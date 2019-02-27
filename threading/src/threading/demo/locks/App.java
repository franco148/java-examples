package threading.demo.locks;

/**
 * Multiple LOCKS; using SYNCHRONIZED code blocks
 *
 * --------------------------------------------------------------------------------------------------------
 * The synchronized keyword prevents a block of code or method from being executed by more than one thread.
 * The larger a protected area is, the longer a blocked thread must wait. The reason is simple: the other
 * thread that is holding the lock needs more time to execute the synchronized code.
 *
 * A method is often bigger than a code block, hence a synchronized method is slower than a block.
 * --------------------------------------------------------------------------------------------------------
 * */
public class App {

    public static void main(String[] args) {
        new Worker().main();
    }
}
