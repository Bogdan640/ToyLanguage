package Controller;

import Exceptions.ControllerException;
import Exceptions.MyException;
import Model.DataStructures.Interfaces.MyIStack;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Values.Classes.RefValue;
import Model.Values.Interfaces.IValue;
import Repository.IRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ctrl implements ICtrl{

    private IRepo repo;
    private boolean displayFlag;

    public Ctrl(IRepo repo, boolean displayFlag){
        this.repo=repo;
        this.displayFlag=displayFlag;
    }

    public Ctrl (IRepo repo){
        this.repo=repo;
        this.displayFlag=false;
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
    @Override
    public PrgState executeOneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty())
            throw ControllerException.is_empty("CTRL ERROR: Execution stack is empty");
        IStmt crtStmt = stk.pop();
        crtStmt.execute(state);
//        if(displayFlag)
//            System.out.println(repo.getCrtPrg());
        return state;
    }

    @Override
    public void executeAll() throws MyException, IOException {
        PrgState prg =  repo.getCrtPrg();
        repo.logPrgStateExec();
        while(!prg.getExeStack().isEmpty()){
            executeOneStep(prg);
            repo.logPrgStateExec();

            List<Integer> symTableAddresses = getAddrFromSymTable(prg.getSymTable().getContent().values());
            Map<Integer, IValue> heapContent = prg.getHeap().getContent();
            List<Integer> addresses = getAllAddresses(symTableAddresses, heapContent);
            prg.getHeap().setContent(unsafeGarbageCollector(addresses, heapContent));
            this.repo.logPrgStateExec();

            if(displayFlag)
                System.out.println(prg);

        }

    }

    public void change_current_state(int index){
        repo.change_current_state(index);
    }





}
