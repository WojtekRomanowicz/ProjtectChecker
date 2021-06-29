package checkers;

public interface Player {

    String name = null;

     default String getName(){
        return name;
    }

    default void makeMove(){

    }
}
