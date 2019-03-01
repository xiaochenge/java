package gx.web.mvc.verticles;

public final class Common implements Runnable {


    static Integer i=1;

    @Override
    public void run() {
        synchronized (this){
           for (int j=1;j<1000;j++){
               if(i == 100)return;
               Thread thread = Thread.currentThread();
               System.out.println(thread.getName()+"**********"+i);
               i++;
           }
        }
    }

    public static void main(String[] args) {
        Common common1 = new Common();
        Common common2 = new Common();
        Common common3 = new Common();
        Thread thread1 = new Thread(common1);
        Thread thread2 = new Thread(common2);
        Thread thread3 = new Thread(common3);
        thread1.start();
        thread2.start();
        thread3.start();

    }




}
