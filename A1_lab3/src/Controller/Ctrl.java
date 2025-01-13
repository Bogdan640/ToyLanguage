package Controller;

import Exceptions.MyException;
import Model.PrgState;
import Model.Values.Classes.RefValue;
import Model.Values.Interfaces.IValue;
import Repository.IRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Ctrl implements ICtrl{

    private IRepo repo;
    private boolean displayFlag;
    public ExecutorService executor;

    public Ctrl(IRepo repo, boolean displayFlag){
        this.repo=repo;
        this.displayFlag=displayFlag;
    }

    public Ctrl (IRepo repo){
        this.repo=repo;
        this.displayFlag=false;
    }

    public IRepo getRepo() {
        return repo;
    }


    public void set_displayFlag(boolean flag){
        this.displayFlag=flag;
    }



    private Map<Integer, IValue> unsafeGarbageCollector(List<Integer> usedAddr, Map<Integer, IValue> heap) {

        return heap.entrySet().stream()
                .filter(e -> usedAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .toList();
    }



    private Integer getMissingUsedAddress(List<Integer> usedAddr, Map<Integer, IValue> heap){

        return heap.entrySet().stream()
                .filter(e -> {
                    return (usedAddr.contains(e.getKey()) && e.getValue() instanceof RefValue
                            && !(usedAddr.contains(((RefValue) e.getValue()).getAddress())));
                })
                .map(e -> ((RefValue) e.getValue()).getAddress())
                .findFirst()
                .orElse(null);
    }


    private List<Integer> getAllAddresses(List<Integer> symTableAddresses, Map<Integer, IValue> heapContent){

        List<Integer> addresses = new ArrayList<>(symTableAddresses);
        Integer missingAddress = getMissingUsedAddress(addresses, heapContent);
        if (missingAddress != null) {
            addresses.add(missingAddress);
        }

        while(missingAddress != null) {
            missingAddress = getMissingUsedAddress(addresses, heapContent);
            if (missingAddress != null) {
                addresses.add(missingAddress);
            }
        }

        return addresses;
    }
//    @Override
//    public PrgState executeOneStep(PrgState state) throws MyException {
//        MyIStack<IStmt> stk = state.getExeStack();
//        if (stk.isEmpty())
//            throw ControllerException.is_empty("CTRL ERROR: Execution stack is empty");
//        IStmt crtStmt = stk.pop();
//        crtStmt.execute(state);
////        if(displayFlag)
////            System.out.println(repo.getCrtPrg());
//        return state;
//    }

    public Map<Integer, IValue> conservativeGarbageCollector(List<PrgState> prgStates){
        List<Integer> symTablesAddresses = prgStates.stream()
                .flatMap(prg -> getAddrFromSymTable(prg.getSymTable().getContent().values()).stream())
                .collect(Collectors.toList());
        Map<Integer, IValue> heap = prgStates.getFirst().getHeap().getContent();
        List<Integer> reachable = getAllAddresses(symTablesAddresses, heap);

        return heap.entrySet().stream()
                .filter(entry -> reachable.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }



    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {

                repo.logPrgStateExec(prg);

                //displayPrgState();
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });


        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() ->{return p.oneStep();}))
                .collect(Collectors.toList());


        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {try {return future.get();} catch (Exception e) {throw new RuntimeException(e);}})
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        repo.setPrgList(prgList);
    }


    @Override
    public void allStep() throws MyException, IOException, InterruptedException {

        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while(!prgList.isEmpty()){
            conservativeGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();




//        PrgState prg =  repo.getCrtPrg();
//        repo.logPrgStateExec();
//        while(!prg.getExeStack().isEmpty()){
//            executeOneStep(prg);
//            repo.logPrgStateExec();
//
//            List<Integer> symTableAddresses = getAddrFromSymTable(prg.getSymTable().getContent().values());
//            Map<Integer, IValue> heapContent = prg.getHeap().getContent();
//            List<Integer> addresses = getAllAddresses(symTableAddresses, heapContent);
//            prg.getHeap().setContent(unsafeGarbageCollector(addresses, heapContent));
//            this.repo.logPrgStateExec();
//
//            if(displayFlag)
//                System.out.println(prg);

//        }

    }


    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().
                filter(p -> p.isNotCompleted()).
                collect(Collectors.toList());
    }

    public void displayPrgState() {
        List<PrgState> prgList = repo.getPrgList();
        if (prgList.isEmpty())
            System.out.println("The program state list is empty");
        else
            prgList.forEach(System.out::println);
    }

    public void change_current_state(int index){
        repo.change_current_state(index);
    }





}
