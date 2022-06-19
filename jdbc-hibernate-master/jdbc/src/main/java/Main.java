import example.CommandOperation;
import example.PreparedstatementOperation;
import example.QueryOperation;
import example.TransactionOperation;

public class Main {

    public static void main(String[] args){
        System.out.println("------------Query operation-------------");
        QueryOperation.queryOperation();
        System.out.println("------------Command operation-----------");
        CommandOperation.commandOperation();
        System.out.println("------------Preparedstatement-----------");
        PreparedstatementOperation.preparedstatementOperation();
        System.out.println("------------Transaction-----------------");
        TransactionOperation.transactionOperation();
    }
}
