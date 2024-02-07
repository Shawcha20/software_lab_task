// Interface defining basic TV operations
interface TV {
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int volume);
    int getChannel();
    void setChannel(int channel);
}

// GeneralTV implementing TV interface
class GeneralTV implements TV {
    private boolean switch_on;
    private int volume;
    private int channel;

    public GeneralTV() {
        switch_on = false;
        volume = 0;
        channel = 0;
    }

    @Override
    public boolean isEnabled() {
        return switch_on;
    }

    @Override
    public void enable() {
        switch_on = true;
    }

    @Override
    public void disable() {
        switch_on = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }
}

// SmartTV extending GeneralTV
class SmartTV extends GeneralTV {
    public void youtube() {
        System.out.println("Opening YouTube");
    }
}

// Abstract class defining basic Remote operations
abstract class Remote {
    protected TV tv;

    public Remote(TV tv) {
        this.tv = tv;
    }

    public void togglePower() {
        if (tv.isEnabled()) {
            tv.disable();
        } else {
            tv.enable();
        }
        System.out.println("Switch " + (tv.isEnabled() ? "on" : "off"));
    }

    public void volumeUp() {
        if (tv.isEnabled()) {
            int currentVolume = tv.getVolume();
            tv.setVolume(currentVolume + 1);
            System.out.println("Increase volume ");
        } else {
            System.out.println("Switch Off");
        }
    }

    public void volumeDown() {
        if (tv.isEnabled()) {
            int currentVolume = tv.getVolume();
            tv.setVolume(currentVolume - 1);
            System.out.println("Decrease volume ");
        } else {
            System.out.println("Switch off");
        }
    }

    public void channelUp() {
        if (tv.isEnabled()) {
            int currentChannel = tv.getChannel();
            tv.setChannel(currentChannel + 1);
            System.out.println("Channel change ");
        } else {
            System.out.println("Switch off");
        }
    }

    public void channelDown() {
        if (tv.isEnabled()) {
            int currentChannel = tv.getChannel();
            tv.setChannel(currentChannel - 1);
            System.out.println("Channel change ");
        } else {
            System.out.println("power off");
        }
    }
}

// GeneralRemote extending Remote
class GeneralRemote extends Remote {
    public GeneralRemote(TV tv) {
        super(tv);
    }
}

// SmartRemote extending Remote
class SmartRemote extends Remote {
    public SmartRemote(TV tv) {
        super(tv);
    }

    public void showYoutube() {
        // Only SmartTV can show YouTube
        if (tv instanceof SmartTV) {
            ((SmartTV) tv).youtube();
        } else {
            System.out.println("Cannot show YouTube. Not a SmartTV.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GeneralTV generalTV = new GeneralTV();
        SmartTV smartTV = new SmartTV();
        Remote generalRemote = new GeneralRemote(generalTV);
        SmartRemote smartRemote = new SmartRemote(smartTV);
        generalRemote.togglePower();
        generalRemote.volumeUp();
        generalRemote.channelUp();
        generalRemote.channelDown();
        generalRemote.volumeDown();
        smartRemote.togglePower();
        smartRemote.showYoutube();
    }
}
