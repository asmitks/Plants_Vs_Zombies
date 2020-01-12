package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.geom.Rectangle2D;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.net.URL;

public class Main extends Application {
    public static MediaPlayer a;
    public static MediaPlayer b;
    static Stage stg;
    static Controller_play controller_play;
    static Controller_new NewController;
    static Controller_load LoadController;
    static Controller_pause PauseController;
    static Controller_level LevelController;
    static Controller controller;
    static Controller_lose controller_lose;
    static ArrayList<Plant> plants;
    static ArrayList<Zombie> zombies;
    static  ArrayList<Rectangle2D> plantRect;
    static  ArrayList<Rectangle2D> ZombieRect;

    public Main() throws IOException {
        URL resource = getClass().getResource("/music/Angry-Birds-Theme-Song.mp3");
        a = new MediaPlayer(new Media(resource.toString()));
        resource = getClass().getResource("/music/background.wav");
        b = new MediaPlayer(new Media(resource.toString()));
        a.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                a.seek(Duration.ZERO);
            }
        });
        b.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                b.seek(Duration.ZERO);
            }
        });
        plants = new ArrayList<>();
        zombies = new ArrayList<>();

    }

    public Rectangle createBoundsRectangle(Bounds bounds) {
        Rectangle rect = new Rectangle();
        rect.setX(bounds.getMinX());
        rect.setY(bounds.getMinY());
        rect.setWidth(bounds.getWidth());
        rect.setHeight(bounds.getHeight());
        return rect;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Plants Vs. Zombies");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
        URL resource = getClass().getResource("/music/Angry-Birds-Theme-Song.mp3");
        a.play();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(lawn_play.class.getResource("lawn"+".fxml"));
        Parent rootLayout = loader.load();
        controller_play = loader.getController();

        FXMLLoader loader11 = new FXMLLoader();
        loader11.setLocation(lose.class.getResource("lose"+".fxml"));
        rootLayout = loader11.load();
        controller_lose = loader11.getController();

        FXMLLoader loader0 = new FXMLLoader();
        loader0.setLocation(Main.class.getResource("sample"+".fxml"));
        rootLayout = loader0.load();
        controller = loader0.getController();

        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(newG.class.getResource("char_sel"+".fxml"));
        rootLayout = loader1.load();
        NewController = loader1.getController();

        FXMLLoader loader2 = new FXMLLoader();
        URL url = Load.class.getResource("load_char"+".fxml");
        loader2.setLocation(url);
        rootLayout = loader2.load();
        LoadController = loader2.getController();

        FXMLLoader loader3 = new FXMLLoader();
        loader3.setLocation(PauseGame.class.getResource("pause_menu"+".fxml"));
        rootLayout = loader3.load();
        PauseController = loader3.getController();

        FXMLLoader loader4 = new FXMLLoader();
        loader4.setLocation(level_page.class.getResource("level_select"+".fxml"));
        rootLayout = loader4.load();
        LevelController = loader4.getController();

        Main m = new Main();

        controller_play.peaShooter.setOnDragDetected(new EventHandler<MouseEvent>() {
            boolean f = false;
            public void handle(MouseEvent event) {
                if(f==false){
                    Dragboard db = controller_play.peaShooter.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    Plant p = new Shooter();
                    plants.add(p);
                    content.putImage(p.getImage());
                    db.setContent(content);
                    event.consume();}
                new Thread(() -> {
                    long t= System.currentTimeMillis();
                    long end = t+4000;
                    while(System.currentTimeMillis() < end) {
                        controller_play.peaShooter.setImage(new Image("File:src/sample/peas_shoooun.png"));
                        f=true;
                    }
                    controller_play.peaShooter.setImage(new Image("File:src/sample/peas_shooo.png"));
                    f = false;
                }).start();
            }

        });
        controller_play.wallnut.setOnDragDetected(new EventHandler<MouseEvent>() {
            boolean f = false;
            public void handle(MouseEvent event) {
                if(f==false){
                    Dragboard db = controller_play.wallnut.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    Plant p = new Barrier();
                    plants.add(p);
                    content.putImage(p.getImage());
                    db.setContent(content);
                    event.consume();}
                new Thread(() -> {
                    long t= System.currentTimeMillis();
                    long end = t+4000;
                    while(System.currentTimeMillis() < end) {
                        controller_play.wallnut.setImage(new Image("File:src/sample/wallnutun.png"));
                        f=true;
                    }
                    controller_play.wallnut.setImage(new Image("File:src/sample/wallnut.png"));
                    f = false;
                }).start();
            }

        });

        controller_play.cherry.setOnDragDetected(new EventHandler<MouseEvent>() {

            boolean f = false;
            public void handle(MouseEvent event) {
                if(f==false){
                    Dragboard db = controller_play.cherry.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    Plant p = new BombPlant();
                    plants.add(p);
                    content.putImage(p.getImage());
                    db.setContent(content);
                    event.consume();}
                new Thread(() -> {
                    long t= System.currentTimeMillis();
                    long end = t+4000;
                    while(System.currentTimeMillis() < end) {
                        controller_play.cherry.setImage(new Image("File:src/sample/cherryun.png"));
                        f=true;
                    }
                    controller_play.cherry.setImage(new Image("File:src/sample/cherry.png"));
                    f = false;
                }).start();
            }

        });

        controller_play.sunFlower.setOnDragDetected(new EventHandler<MouseEvent>() {
            boolean f = false;
            public void handle(MouseEvent event) {
                if(f==false){
                Dragboard db = controller_play.sunFlower.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                Plant p = new SunPlant();
                plants.add(p);
                content.putImage(p.getImage());
                db.setContent(content);
                event.consume();}
                new Thread(() -> {
                    long t= System.currentTimeMillis();
                    long end = t+4000;
                    while(System.currentTimeMillis() < end) {
                        controller_play.sunFlower.setImage(new Image("File:src/sample/sunflowerun.png"));
                        f=true;
                    }
                    controller_play.sunFlower.setImage(new Image("File:src/sample/sunflower.png"));
                    f = false;
                }).start();
            }
        });

//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

        NewController.create.setOnAction(e -> {
            if((!NewController.name.getText().isEmpty() && (NewController.dob.getValue()!=null)))
            {
                String Name = String.valueOf(NewController.name.getText());
                LocalDate date = NewController.dob.getValue();
                User new_user =new User(Name,date);
                new_user.login();
                AudioClip audio = new AudioClip("file:src/music/buttonclick.wav");
                audio.play();
                try {
                    Main.stg.setScene(new Scene(Main.controller_play.overall, 1280, 800));
                    Main.stg.show();
                } catch(Exception exc) {
                    System.out.println("Error1");
                }

            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input fields empty or wrong input");
                alert.setContentText("Please fill all input fields correctly");
                alert.showAndWait();
            }
        });

        final Level[] level0 = new Level[1];

        LevelController.l1.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event) {
                AudioClip audio = new AudioClip("file:src/music/buttonclick.wav");
                audio.play();
                Level level =new Level(1);
                level0[0] = level;
            }
        });

        LevelController.l2.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event) {
                AudioClip audio = new AudioClip("file:src/music/buttonclick.wav");
                audio.play();
                Level level =new Level(2);
                level0[0] = level;
            }
        });
        LevelController.l3.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event) {
            AudioClip audio = new AudioClip("file:src/music/buttonclick.wav");
            audio.play();
            Level level =new Level(3);
            level0[0] = level;
        }
        });

        LevelController.l4.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event) {
                AudioClip audio = new AudioClip("file:src/music/buttonclick.wav");
                audio.play();
                Level level =new Level(4);
                level0[0] = level;
            }
    });

        LevelController.l4.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event) {
                AudioClip audio = new AudioClip("file:src/music/buttonclick.wav");
                audio.play();
                Level level =new Level(5);
                level0[0] = level;
            }
        });

        Backyard backyard = m.new Backyard(new Level(5));
        backyard.generate_suntoken();
        backyard.generate_Zombie(0);
        System.out.println(123);
}

    public Stage getStg() {
        return stg;
    }


//
//    private final ChangeListener<Number> checkIntersection = (ob, n, n1)->{
//        if (iv_plane.getBoundsInParent().intersects(iv_ship.getBoundsInParent())){
//            System.out.println("Intersection detected");
//        }
//    };
//    private ImageView iv_ship, iv_plane;
//
//    private void drawGame (Stage primaryStage) {
//        iv_ship = new ImageView();
//        iv_plane = new ImageView();
//    ...
//        iv_ship.translateXProperty().addListener(checkIntersection);
//        iv_ship.translateYProperty().addListener(checkIntersection);
//    ...
//        root.getChildren().addAll(iv_plane,iv_ship);
//        primaryStage.setScene(scene_game);
//    }


    public static void setStg(Stage stg) {
        Main.stg = stg;
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

//####################################################EXCEPTIONS#############################################################################

class Sun_collected_exception extends Exception {

}

class Plant_placed_exception extends Exception {

}

class Zombie_and_Plant_on_same_tile extends Exception {

}

class Zombie_ate_plant extends Exception {

}

class Zombie_blown_by_plant extends Exception {

}

class Zombie_blocked_by_plant_exception extends Exception {

}

class Zombie_shot_by_plant_exception extends Exception {

}

class suntoken_gen_by_plant_exception extends Exception {

}

class Zombie_died_exception extends Exception {

}

class Plant_purchased_exception extends Exception {

}

class lawnmower_activated_exception extends Exception {

}

class zombie_reached_house_exception extends Exception {

}

class level_cleared_exception extends Exception {

}

class mission_failed_exception extends Exception {

}

class game_won_exception extends Exception {

}


//###############################################CONTROL##################################################################################################

interface Control {
    public void control();
}

class Scorecard implements Control {
    private ArrayList<Plant> available_plants;
    private int num_tokens;
    private int score;
    private ImageView scoreboard;
    private Image sc;

    @Override
    public void control() {

    }

    //ASMIT -  ADD PATH TO IMAGES in image and imageview
    public Scorecard() {
        this.score = 0;
        this.num_tokens = 0;
        this.available_plants = new ArrayList<Plant>();

    }

    //
//    public void Display_score(){
//;
//    }
    public ArrayList<Plant> getAvailable_plants() {
        return available_plants;
    }

    public int getScore() {
        return score;
    }

    public void increase_score() {
        this.score += 50;
        this.num_tokens++;
    }
}

class Inventory implements Control {
    private ArrayList<Plant> purchased_plants;
    private int quantity;
    private ImageView inventroy;
    private Image inv;

    @Override
    public void control() {

    }

    //ASMIT -  ADD PATH TO IMAGES in image and imageview
    public Inventory() {
        purchased_plants = new ArrayList<Plant>();
        quantity = 0;


    }


    public ArrayList<Plant> getPurchased_plants() {
        return purchased_plants;
    }

    public void set_quantity(int q, Plant p) {

    }

    public void purchase_Plant(Plant plant) {
        purchased_plants.add(plant);

    }
}

class ProgressBar implements Control {
    int progress;
    int level;
    private ImageView prog;
    private Image pr;
    private ImageView progressBar;
    private Image prb;

    public ProgressBar() {
        progress = 0;
    }

    @Override
    public void control() {
        ;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

}

class Database implements Control {
    private String save_game_filename;

    @Override
    public void control() {

    }

    public String getSave_game_filename() {
        return save_game_filename;
    }

    public void setSave_game_filename(String save_game_filename) {
        this.save_game_filename = save_game_filename;
    }

    public void serialize(PlantsvsZombies game) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(save_game_filename));
            out.writeObject(game);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void deserialize(PlantsvsZombies game) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(save_game_filename));
            PlantsvsZombies newGame = (PlantsvsZombies) in.readObject();
        } finally {
            in.close();
        }
    }
}


abstract class Menu implements Control {
    @Override
    public void control() {

    }

    public boolean is_Active() {
        return false;
    }

    abstract public void Display();
}

class MainMenu extends Menu {
    @Override
    public void Display() {

    }

    public void PlayGame() {

    }

    public void LoadGame() {

    }
}

class ResumeMenu extends Menu {
    @Override
    public void Display() {

    }

    public void resume() {

    }

    public void save_and_exit() {

    }
}

class IngameMenu extends Menu {
    @Override
    public void Display() {

    }

    public void Continue() {

    }

    public void save_and_exit() {

    }
}

//#####################################################33PLANTSVSZOMBIES########################################################

class PlantsvsZombies implements Serializable {
    private User newUser;
    private Backyard newBckyard;

    public PlantsvsZombies() {
        ;
    }

    public void playGame() {

    }

    public void loginPlayer(String Playername) {

    }

    public void pause() {

    }

    public void save() {

    }

    public void Load() {

    }

    public void Quit() {

    }

    public void DisplayMainMenu() {

    }

    public void DisplayResumeMenu() {

    }

    public void DisplayIngameMenu() {

    }

    public void MovePlayerToNextLevel() {

    }

    public void ResetGame() {

    }

    public void GameWon() {

    }

    public void GameOver() {

    }
}

class User implements Serializable {
    private String Name;
    private boolean isLoggedin;
    LocalDate date;

    public User(String name, LocalDate date) {
        Name = name;
        isLoggedin = false;
        this.date = date;
    }
    //ASMIT
    public void login() {

    }

    public String getName() {
        return Name;
    }

    public boolean isLoggedin() {
        return isLoggedin;
    }
}

class Player extends User implements Serializable {
    private Level curr_level;
    private Inventory inventory;
    private int score;
    private Scorecard available_Plant;
    private LocalDate date;

    public Player(String name,LocalDate date) {
        super(name, date);
    }

    public Level getCurr_level() {
        return curr_level;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getScore() {
        return score;
    }

    public Scorecard getAvailable_Plant() {
        return available_Plant;
    }

    public void level_up() {
        if (curr_level.isFinished()) {
            this.curr_level.setCurr_level(this.curr_level.getCurr_level() + 1);
        }
    }

    public void PurchasePlant(Plant plant) {
        inventory.purchase_Plant(plant);
    }
//    public void PlacePlant(){
//
//    }
//    public void CollectSun(){
//
//    }


    public void play() {

    }

    public void pause() {

    }

}


interface world {
    void initialise();
}


class Tile implements world, Serializable {
    private Plant curr_plant;
    private Zombie curr_zombie;
    private Position position;
    private boolean isempty;

    public Tile() {
        isempty = true;
    }

    @Override
    public void initialise() {
        curr_plant = null;
        curr_zombie = null;
    }

    public Tile(Position pos) {
        this.position = pos;
    }

    public boolean has_plant() {
        if (curr_plant != null) {
            return true;
        }
        return false;
    }

    public boolean has_zombie() {
        if (curr_zombie != null) {
            return true;
        }
        return false;
    }

    public Plant getCurr_plant() {
        return curr_plant;
    }

    public Zombie getCurr_zombie() {
        return curr_zombie;
    }

    public Position getPosition() {
        return position;
    }

    public void setCurr_plant(Plant curr_plant) {
        if (isempty) {
            this.curr_plant = curr_plant;
            isempty = false;
        }
    }

    public void setCurr_zombie(Zombie curr_zombie) {
        this.curr_zombie = curr_zombie;
    }
}


class Lawn implements world, Serializable {
    private ArrayList<Tile> Tiles;
    private Level curr_level;
    private ArrayList<LawnMower> mowers;

    public Lawn() {
        Tiles = new ArrayList<Tile>();
        curr_level = new Level(1);
        mowers = new ArrayList<LawnMower>();
        initialise();
    }

    @Override
    public void initialise() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                Tiles.add(new Tile(new Position(i, j)));
            }
        }
        for (int i = 0; i < 5; i++) {
            mowers.add(new LawnMower(new Position(i, 0)));
        }

    }

    public Level getCurr_level() {
        return curr_level;
    }

    public void setCurr_level(Level curr_level) {
        this.curr_level = curr_level;
    }
}


class Backyard implements world, Serializable {
    private Lawn curr_lawn;
    private Level level;
    public Lawn getCurr_lawn() {
        return curr_lawn;
    }

    public Backyard(Level level) {
        this.level=level;
        System.out.println(123123);
        initialise();
    }

    @Override
    public void initialise() {
        curr_lawn = new Lawn();
    }
    public void start_level()
    {
        // int zombie codes 1= vanilla; 2=cone; 3=football;
        // vel
        wave_1();
        wave_2();

    }
    private void wave_1()
    {
        if(this.level.getCurr_level()==1)
        {
            this.generate_Zombie(0);
        }
        else if(this.level.getCurr_level()==2)
        {
            this.generate_Zombie(0);
            this.generate_Zombie(0);
            this.generate_Zombie(0);

        }
        else if(this.level.getCurr_level()==3)
        {
            this.generate_Zombie(0);
            this.generate_Zombie(0);
            this.generate_Zombie(1);
            this.generate_Zombie(1);

        }
        else if(this.level.getCurr_level()==4)
        {
            this.generate_Zombie(0);
            this.generate_Zombie(1);
            this.generate_Zombie(2);

        }
        else if(this.level.getCurr_level()==5)
        {
            this.generate_Zombie(2);
            this.generate_Zombie(2);
            this.generate_Zombie(1);
            this.generate_Zombie(0);
            this.generate_Zombie(0);

        }
    }
    private void wave_2()
    {
        if(this.level.getCurr_level()==1)
        {
            this.generate_Zombie(0);
            this.generate_Zombie(0);

        }
        else if(this.level.getCurr_level()==2)
        {
            this.generate_Zombie(0);
            this.generate_Zombie(0);
            this.generate_Zombie(1);

        }
        else if(this.level.getCurr_level()==3)
        {
            this.generate_Zombie(1);
            this.generate_Zombie(2);
            this.generate_Zombie(1);

        }
        else if(this.level.getCurr_level()==4)
        {
            this.generate_Zombie(0);
            this.generate_Zombie(2);
            this.generate_Zombie(2);
            this.generate_Zombie(1);


        }
        else if(this.level.getCurr_level()==5)
        {
            this.generate_Zombie(2);
            this.generate_Zombie(2);
            this.generate_Zombie(1);
            this.generate_Zombie(2);
            this.generate_Zombie(1);
            this.generate_Zombie(1);
        }
    }
    public void generate_Zombie(int a) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    // convert this such that only one zombie is generated ie. one cycle
                    public void run() {
                        int min = 0;
                        int max = 20;
                        double x = ((Math.random() * ((max - min) + 1)) + min);
                        int a = 1;
                        if(10<=x && x<16){
                            a=2;
                        }
                        else if(10<=x && x<16){
                            a=3;
                        }
                        Zombie zombie;
                        if(a==3){
                            zombie = new FootballZombie();
                        }
                        else if (a==1){
                            zombie = new vanillaZombie();
                        }
                        else {
                            zombie =new ConeHead();
                        }
                        double y = 0;
                        double w = ((Math.random() * ((5 - 1) + 1)) + 1);
                        if (w<1) {
                            y = 114;
                        } else if (1<= w && w<2) {
                            y=241;

                        } else if (2<= w && w<3) {
                            y=383;

                        } else if (3<= w && w<4) {
                            y=510;

                        } else if (4<= w) {
                            y=635;
                        }
                        zombie.setX(1280+x);
                        zombie.setY(y);
                        zombies.add(zombie);
                        zombie.move();
                        controller_play.overall.getChildren().add(zombie);
                    }
                });
            }
        } , 0, (int) ((Math.random() * ((12 - 4) + 1)) + 4)* 1000);
    }

    public void generate_suntoken() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Main.SunToken s = new Main.SunToken();
                int min = 0;
                int max = 1200;
                s.setY(0);
                double x = ((Math.random() * ((max - min) + 1)) + min);
                s.setX(x);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller_play.overall.getChildren().add(s);
                        TranslateTransition tt5;
                        tt5 = new TranslateTransition();
                        tt5.setNode(s);
                        tt5.setDuration(new Duration(10000));
                        tt5.setToY(740);
                        tt5.play();
                    }
                });
            }
        } , 0, 10 * 1000);
    }

    public void launch_lawnmower() {

    }
}

class Level implements world, Serializable {
    private int curr_level;
    ProgressBar progressBar;

    @Override
    public void initialise() {
        ;
    }

    public boolean isFinished() {
        if (progressBar.getProgress() == 100) {
            progressBar.setLevel(progressBar.getLevel() + 1);
            progressBar.setProgress(0);
            return true;
        }
        return false;
    }

    public Level(int curr_level) {
        this.curr_level = curr_level;
        progressBar = new ProgressBar();
    }

    public int getCurr_level() {
        return curr_level;
    }

    public void setCurr_level(int curr_level) {
        this.curr_level = curr_level;
    }
}

class Position implements world, Serializable {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void initialise() {
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void set_pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

interface Character extends world {
    public void Act();
}

interface Friend extends Character {
    public void Use();
}

interface Foe extends Character {
    public void Eat(Plant p);
}

class Plant extends ImageView implements Friend, Serializable {
    protected int health;
    protected Position pos;
    protected Image plt;

    public Plant(){
        health = 100;
        beingEaten();
    }

    @Override
    public void initialise() {

    }

    @Override
    public void Use() {

    }

    @Override
    public void Act() {

    }

    public void beingEaten(){
        Plant p = this;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if(p.getHealth()<0){
                    Platform.runLater(() -> controller_play.gridPane.getChildren().remove(p));
                }
            }
        }, 0, 17);

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


}

class Bullet extends ImageView implements Friend, Serializable {
    private float vel;
    private boolean hit_zombie;
    private boolean out_of_screen;
    protected Image p;

    public Bullet() {
        //Change
        vel = 5;
        hit_zombie = false;
        out_of_screen = true;
        p = new Image("File:src/sample/Pea.png");
        this.setImage(p);
    }

    @Override
    public void initialise() {

    }

    @Override
    public void Use() {
    }

    public void shoot() {
        Platform.runLater(() -> controller_play.overall.getChildren().add(this));
        this.toFront();
        Bullet b = this;
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            public void run() {
                if(b.checkCollision()) {
                    Platform.runLater(() -> controller_play.overall.getChildren().remove(b));
                    cancel();
                }
                if(b.getX()>1280){
                    Platform.runLater(() -> controller_play.overall.getChildren().remove(b));
                    cancel();
                }
            }
        }, 0,  17);
    }

    @Override
    public void Act() {
    }

    public boolean checkCollision(){
        Bullet z =this;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                z.setX(z.getX()+5);
            }
        });
        for (Zombie zombie : zombies) {
            Bounds zo = this.localToScene(this.getBoundsInLocal());
            Bounds pl = zombie.localToScene(zombie.getBoundsInLocal());
            if(zo.intersects(pl)){
                System.out.println("int");;
                zombie.setHealth(zombie.getHealth()-20);
                System.out.println(zombie.getHealth());
                return true;
            }
        }
        return false;
    }

//    public void shoot(){
//        Bullet s = this;
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                TranslateTransition tt5;
//                tt5 = new TranslateTransition();
//                tt5.setNode(s);
//                tt5.setDuration(new Duration(10000));
//                tt5.setCycleCount(50);
//                tt5.setCycleCount(Timeline.INDEFINITE);
//                tt5.setToX(1000);
//                tt5.play();
//            }
//        });
//    }

    public float getVel() {
        return vel;
    }

    public void setVel(float vel) {
        this.vel = vel;
    }

    public void hit_zombie() {

    }

    public void out_of_screen() {

    }
}

class Shooter extends Plant {
    protected Bullet pea;
    protected int interval_shot;
    public Shooter(){
        plt=new Image("File:src/sample/pea_shooter.gif");
        this.setImage(plt);
        this.shoot();
    }

    public Shooter getShooter(){
        return this;
    }

    public void shoot() {
        Timer timer = new Timer();
        Plant p = this;
        timer.schedule(new TimerTask() {
            public void run() {
                Bullet s = new Bullet();
                s.setX(p.getX()+60);
                s.setY(p.getY()+40);
                s.shoot();
            }
        } , 0, 2 * 1000);
    }
}

//class Peashooter extends Shooter {
//    private int num_peas_fired = 1;
//
//    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot
//    public Peashooter() {
//
//    }
//}
//
//class Threepeater extends Shooter {
//    private int num_peas_fired = 3;
//
//    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot
//}

class Barrier extends Plant {
    public Barrier() {
        health = 200;
        plt=new Image("File:src/sample/walnut_full_life.gif");
        this.setImage(plt);

    }

    public void Protect() {

    }
    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot
}

class SunPlant extends Plant {
    public SunPlant() {
        plt=new Image("File:src/sample/sun_flower.gif");
        this.setImage(plt);
        generate_sun();
    }
    public void generate_sun() {
        Timer timer = new Timer();
        Plant p = this;
        timer.schedule(new TimerTask() {
            double x = ((Math.random() * ((10 +10) + 1)) -10);
            public void run() {
                SunToken s = new SunToken();
                s.setX(p.getX()+x);
                s.setY(p.getY()-x);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller_play.overall.getChildren().add(s);
                    }
                });
            }
        } , 0, 8 * 1000);
    }

}

class BombPlant extends Plant {
    protected boolean has_exploded;
    protected boolean is_near_zombie;

    public BombPlant() {
        plt=new Image("File:src/sample/Cherrybomb.png");
        this.setImage(plt);
    }

    public void explode() {
        //ASMIT add path to bomb sound
        AudioClip audio = new AudioClip("");
        audio.play();
    }
    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot
}

//class PotatoMine extends BombPlant {
//    private int activation_time = 14;
//    private int area = 1;
//
//    public PotatoMine() {
//        plt=new Image("File:src/sample/PotatoMine_body.png");
//        this.setImage(plt);
//
//    }
//    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot
//}
//
//class Cherry_Bomb extends BombPlant {
//    public Cherry_Bomb() {
//        plt=new Image("File:src/sample/Cherrybomb.png");
//        this.setImage(plt);
//    }
//
//    private double activation_time = 1.2;
//    private int area = 9;
//    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot
//}


class SunToken extends ImageView implements Friend, Serializable {
    private Position pos;
    private float vel;
    private int points;
    private Image sun;

    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot
    public SunToken() {
        sun = new Image("File:src/sample/sun.gif");
        this.setImage(sun);
        SunToken s = this;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                controller_play.overall.getChildren().remove((ImageView) s);
                controller_play.score.setText(String.valueOf((Integer.parseInt(controller_play.score.getText())+25)));
            }
        });
    }

    @Override
    public void initialise() {

    }

    @Override
    public void Use() {

    }

    @Override
    public void Act() {

    }

    public ImageView getSuntoken() {
        return this;
    }

    public void setSuntoken(ImageView suntoken) {
        this.setImage(suntoken.getImage());
    }

    public Position getPos() {
        return pos;
    }

    public float getVel() {
        return vel;
    }

    public int getPoints() {
        return points;
    }

}


class LawnMower extends ImageView implements Friend, Serializable {
    private boolean is_avialable;
    private boolean has_zombie_reached;
    private Position p;
    private Image mow;

    public LawnMower(Position p) {
        this.p = p;
        mow=new Image("File:src/sample/lawn_mower.gif");
        this.setImage(mow);

    }
    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot

    @Override
    public void Act() {

    }

    @Override
    public void Use() {

    }

    @Override
    public void initialise() {

    }

    public void run_lawnmower() {
        //ASMIT add path to lawnmower sound
        AudioClip audio = new AudioClip("");
        audio.play();

    }
}

class Zombie extends ImageView implements Foe, Serializable {
    protected int health;
    protected Image zomb;
    private TranslateTransition tt5;
    private int damage;

    public Zombie(int d,int h){
        tt5 = new TranslateTransition();
        damage= d;
        health = h;
    }

    @Override
    public void initialise() {
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void Eat(Plant p) {

    }

    @Override
    public void Act() {

    }

    public boolean checkCollision(){
        Zombie z =this;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                z.setX(z.getX()-1);
            }
        });
            for (Plant plant : plants) {
                Bounds zo = this.localToScene(this.getBoundsInLocal());
                Bounds pl = plant.localToScene(plant.getBoundsInLocal());
                if(zo.intersects(pl)){
                    System.out.println("int");;
                    if(plant.getClass()==BombPlant.class){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                int r = controller_play.gridPane.getRowIndex(plant);
                                int c = controller_play.gridPane.getColumnIndex(plant);
                                ImageView v = new ImageView(new Image(""));
                                controller_play.gridPane.add(v,r,c);
//                                long t= System.currentTimeMillis();
//                                long end = t+1000;
//                                while(System.currentTimeMillis() < end) {
//                                    ;
//                                }
//                                controller_play.gridPane.getChildren().remove(v);
                            }
                        });
                        plant.setImage(new Image("File:src/sample/explode.gif"));
                        die(z);
                        return true;
                    }
                    else {
                        plant.setHealth(plant.getHealth() - z.getDamage());
                    }
                    return true;
                }
            }
            return false;
    }

    public void die(Zombie z){
        System.out.println("die");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller_play.overall.getChildren().remove(z);
                zombies.remove(z);
            }
        });
    }

    public void move() {
        Timer timer = new Timer();
        AudioClip audio = new AudioClip("File:src/music/scream.wav");
        timer.schedule(new TimerTask() {
            public void run() {
                    audio.play();
            }
        }, 0, 4 * 1000);

        Zombie z= this;
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            public void run() {
                if(z.getHealth()<0){
                    die(z);
                    cancel();
                }
                else if(checkCollision()) {
                    cancel();
                }
                else if(z.getX()<150){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Parent root = controller_lose.ap;
                            stg.setScene(new Scene(root,1280,800));
                            stg.show();
                        }
                    });
                    cancel();
                }
            }
        }, 0,  17);

    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}

class ConeHead extends Zombie {
    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot
    private int extra_health;
    public ConeHead(){
        super(10,150);
        zomb = new Image("File:src/sample/SoreWeirdGrunion-max-1mb.gif", 100,130,false,false);
        this.setImage(zomb);
        this.setImage(zomb);
    }
    public void attack() {

    }

    public void defend() {

    }
}

class vanillaZombie extends Zombie {
    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot

    public vanillaZombie(){
        super(5,100);
        zomb = new Image("File:src/sample/zombie_normal.gif");
        this.setImage(zomb);
    }
}

class FootballZombie extends Zombie {
    //ASMIT -  ADD PATH TO IMAGES in image and imageview fill contrucot

    public FootballZombie(){
        super(20,250);
        zomb = new Image("File:src/sample/zombie_football.gif");
        this.setImage(zomb);
    }
    private int extra_health;
    private int extra_attack;

    public void attack() {

    }

    public void defend() {

    }
}
}
