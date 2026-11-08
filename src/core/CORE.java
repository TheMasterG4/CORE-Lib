package core;


import java.nio.file.FileSystems;
import java.util.*;
import java.util.concurrent.*;

import static core.CoreStatus.*;

public final class CORE {
    /// System Info
    public static final int numberOfCores = Runtime.getRuntime().availableProcessors();
    public static final String usersName = System.getProperty("user.name");
    public static final String programLocation= System.getProperty("user.dir");
    public static final String usersHome = System.getProperty("user.home");
    public static final String osArchitecture = System.getProperty("os.arch");
    public static final String osName = System.getProperty("os.name");
    public static final String osVersion = System.getProperty("os.version");


    public static final String fileSeparator = FileSystems.getDefault().getSeparator();


    /// Work Work Work Work Work Wonk Willy Wonka
    public static ExecutorService worker;
    public static ExecutorService lightWorker;
    public static final List<Runnable> todo = new ArrayList<>();
    public static final List<Runnable> mainTodo = new ArrayList<>();
    public static final List<Runnable> smallTodo = new ArrayList<>();
    public static final List<Future<?>> futures = new ArrayList<>();

    /// All Threads being tracked & Thread Control
    public static Thread[] runningThreads;
    public static int count;
    public static final ThreadGroup group;
    private static final ThreadFactory t=Executors.defaultThreadFactory();


    /// Time Specifics
    public static long programDuration=0;
    public static final long startTime=System.currentTimeMillis();


    /// Saving Data
    public static long timeSinceLastSave = 0;
    public static long timeBetweenSaves = 5000;

    /// Status Information
    public static CoreStatus status = INACTIVE;
    public static boolean isActive = false;
    public static boolean programComplete = false;


    /// Program Specific Information
    public static Program program;
    

    private CORE(){}

    static{
        group = Thread.currentThread().getThreadGroup();
        count = group.activeCount();
        runningThreads = new Thread[count];
        group.enumerate(runningThreads);


        /// Initialize Workers
        lightWorker = Executors.newVirtualThreadPerTaskExecutor();
        worker = Executors.newScheduledThreadPool(numberOfCores-runningThreads.length,t);
    }
    ///TODO: STARTUP SYSTEMS
    private static void startup(Program p,
                               boolean lockCores,
                               boolean allowNativeRunning,
                               Runnable... onLoop){

    }


    private static void runBinded(Program p,
                                  Runnable... onLoop){}
    public static void run(Program p,
                            Runnable... onLoop){

        /// Set Up Base Data
        Runnable[] r;
        program = p;



        isActive = true;

        boolean hasTodo;

        /// Loops less than every millisecond, controls the main program
        while(!programComplete || !todo.isEmpty()){

            /// current work, runs main processes + manages tasks
            hasTodo = false;
            if(!todo.isEmpty()){
                hasTodo = true;
                status = RUNNING_HEAVY;
                r = todo.toArray(new Runnable[0]);
                todo.clear();
                for (Runnable runnable : r) {
                    futures.add(worker.submit(runnable));
                }
            }
            if(!smallTodo.isEmpty()){
                if(hasTodo) status = RUNNING_BOTH; else status = RUNNING_LIGHT;
                r = smallTodo.toArray(new Runnable[0]);
                smallTodo.clear();
                for (Runnable runnable : r) {
                    futures.add(worker.submit(runnable));
                }
                hasTodo = true;
            }
            if(!hasTodo) status = IDLE;


            /// Thread Management Loop
            count = group.activeCount();
            runningThreads = new Thread[count];
            group.enumerate(runningThreads);

            /// Forcibly manage executor + keep thread count down
            if(futures.stream()
                    .allMatch(Future::isDone)){
                futures.clear();
                worker.shutdown();
                worker = Executors.newScheduledThreadPool(numberOfCores-runningThreads.length,t);
            }
            /// Check Status

            /// On Loop, KEEP RUNNABLE SMALL
            for(Runnable now:onLoop) now.run();

            if(!mainTodo.isEmpty()){
                Runnable[] now = mainTodo.toArray(new Runnable[0]);
                mainTodo.clear();
                for(Runnable n:now){
                    n.run();
                }
            }

            /// Update Program's Lifetime
            programDuration = System.currentTimeMillis()-startTime;


        }
        /// Kill program
        onShutDown();
    }

    private static void onShutDown(){
        status = SHUTDOWN_SEQUENCE;
        worker.shutdown();
        while(isActive){
            if(futures.stream()
                    .allMatch(Future::isDone)){
                futures.clear();
                worker.shutdown();
                worker = Executors.newScheduledThreadPool(numberOfCores-runningThreads.length,t);
                isActive = false;
            }else Thread.onSpinWait();
        }

    }
    private static void saveData(){

    }



    public static void addSmallTasks(Runnable... r){
        smallTodo.addAll(List.of(r));
    }
    public static void addSmallTasks(Thread... t){
        smallTodo.addAll(List.of(t));
    }
    public static void addTasks(Runnable... rs){
        todo.addAll(List.of(rs));
    }
    public static void addTasks(Thread... ts){
        todo.addAll(List.of(ts));
    }

    public static void addMainTasks(Runnable... rs){
        mainTodo.addAll(List.of(rs));
    }
}
