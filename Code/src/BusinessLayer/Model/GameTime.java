package BusinessLayer.Model;

/**
 * Class GameTime that manages with a thread when the troops are deploying to record or reproduce a game.
 */
public class GameTime implements Runnable {
    private boolean threadRunning;
    private boolean play;
    private long millis;

    /**
     * Instantiates a new Game time.
     */
    public GameTime() {
        this.threadRunning = true;
        this.play = true;
        this.millis = 0;
    }

    private synchronized void logic() {
        try {
            if (play) {
                Thread.sleep(1);
                millis++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (threadRunning) {
            logic();
        }
    }

    /**
     * Gets millis.
     *
     * @return the millis
     */
    public long getMillis() {
        return millis;
    }

    /**
     * Stop thread.
     */
    public void stopThread() {
        threadRunning = false;
    }

    /**
     * Resume thread.
     */
    public void resumeThread() {
        play = true;
    }

    /**
     * Pause thread.
     */
    public void pauseThread() {
        play = false;
    }
}
