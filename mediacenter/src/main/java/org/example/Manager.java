package org.example;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;
/**
*@author Paul Otradovec
 * Manages all the Menus and maintains list of current media on-file
*
*
 *
 * */
public class Manager {
    private long idCounter;
    private final double RENTAL_PRICE=2.00;
    //changing this will change the load and save type of the files the program is compatible with.
    private final String MEDIA_FILE_EXTENSION=".media";
    InputStreamReader iReader;
    BufferedReader bReader;
    Scanner scanIn;
    String saveDir=System.getProperty("user.dir");
    String loadDir=System.getProperty("user.dir");
    ArrayList<Media> loadedMedia;
    ArrayList<Media> currentMedia;

    public Manager() throws IOException, IllegalAccessException {
        iReader= new InputStreamReader(System.in);
        bReader=new BufferedReader(iReader);
        currentMedia = new ArrayList<Media>();
        showMainMenu();
        scanIn= new Scanner(System.in);
        idCounter=0;
    }


    //main meanu screen
    public void showMainMenu() throws IOException, IllegalAccessException {
        scanIn= new Scanner(System.in);
        System.out.println("Welcome to Media Rental System \n1: Load Media objects...\n" +
                "2: Find Media object...\n" + "3: Rent Media object...\n"
                +"4: Create Media object...\n"+"5: Save Media objects\n"
                +"6: CheckIn Media objects\n" +"7: Remove Media object from local DB\n"+"8: Show List of Media Objects\n"+"9: Quit\n"
                + "Enter your selection :");
        //if incorrect info throw
        try {
            switch (scanIn.nextInt()) {
                case 1:
                    loadMediaMenu();
                    break;
                case 2:
                    findMedia();
                    break;
                case 3:
                    rentMedia();
                    break;
                case 4:
                    createMediaMenu();
                    break;
                case 5:
                    saveMedia();
                    break;
                case 6:
                    checkInMedia();
                    break;
                case 7:
                    destroyMediaObject();
                    break;
                case 8:
                    showCurrentMedia();
                case 9:
                    System.exit(0);
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println("Incorrect Entry");
            showMainMenu();
        }


    }
//show currently avaiable
    private void showCurrentMedia() throws IOException, IllegalAccessException {
        System.out.println("List of Current Media on file");
        currentMedia.stream().forEach(System.out::println);
        showMainMenu();
    }
    //rentMedia
    private void rentMedia() throws IOException, IllegalAccessException {
        long id;

        System.out.print("Please Enter the Id for the rental");
        id= scanIn.nextLong();

        //if id passed in is greater than the list size just return to menu
        if(id>currentMedia.size()){
            System.out.println("Id is not valid");
            showMainMenu();
        }
        for (Media media: currentMedia) {
            if(media.getId()==id) {
                if (!media.isAvailable()) {
                    System.out.println(media.getTitle() + " Not Available already checked out");
                } else {
                    media.setAvailable(false);
                    System.out.println("Title# "+media.getTitle() + " Successfully Checked Out Rental Fee $"+String.format("%.2f", RENTAL_PRICE));
                }
            }
        }

        showMainMenu();
    }
    //check In rented media
    private void checkInMedia() throws IOException, IllegalAccessException {
        long id;

        System.out.print("Please Enter the Id for the Return");
        id= scanIn.nextLong();
        for (Media media: currentMedia) {
            if(media.getId()==id){
                //has to be checked out to be checked in
                if(!media.isAvailable()){
                    media.setAvailable(true);
                    System.out.println(media.getTitle()+" Successfully Checked In");
                }else{

                    System.out.println(media.getTitle()+" Never Checked Out");
                }
            }
        }
        showMainMenu();
    }
    //loops thru list and saves media to preferred dir for later use with .media file exstenstion
    private void saveMedia() throws IOException, IllegalAccessException {

        System.out.print("Do you want to save to a specific dir? Y/N");
        if(scanIn.next().trim().equals("Y")){
            System.out.print("Enter the full directory path");
            saveDir=scanIn.next();
        }
        ObjectOutputStream oOutputStream;
        FileOutputStream fOutStream;
        for (Media media: currentMedia) {


            fOutStream= new FileOutputStream(saveDir+"/"+media.getTitle()+media.getId()+MEDIA_FILE_EXTENSION);
            oOutputStream = new ObjectOutputStream(fOutStream);
             oOutputStream.writeObject(media);
            oOutputStream.close();
            fOutStream.close();
        }

        showMainMenu();

    }
    //loops thru media list and find the one that has exact id, contains the title, or contains genre
    private void findMedia() throws IOException, IllegalAccessException {
        String toFind;

        System.out.println("Search by title or id or genre");
        toFind = bReader.readLine();
        for (Media media: currentMedia) {

            if(String.valueOf(media.getId()).equals(toFind)||media.getTitle().toLowerCase().contains(toFind.trim().toLowerCase())||media.getGenre().toLowerCase().contains(toFind.trim().toLowerCase())){
                System.out.println(media.toString());

            }
        }
        showMainMenu();
    }

    //loads files with correct media extenstion into list
    public int loadFiles(String dir) throws IOException {
        loadedMedia= new ArrayList<>();
        ObjectInputStream oOutputStream;
        FileInputStream fInStream;
        File directory = new File(dir);
        try {
            for (File file : directory.listFiles()) {

                if (file.toString().contains(MEDIA_FILE_EXTENSION)) {
                    try {
                        // opening streams
                        fInStream = new FileInputStream(file);
                        oOutputStream = new ObjectInputStream(fInStream);
                        // write object to file
                        Media currentFile = (Media) oOutputStream.readObject();
                        if (!currentMedia.contains(currentFile)){
                            currentFile.setId(idCounter);
                            idCounter++;
                            currentMedia.add(currentFile);
                            loadedMedia.add(currentFile);
                        }else{
                            System.out.println(currentFile.getTitle()+ " Already loaded");

                        }

                        // closing streams
                        oOutputStream.close();
                        fInStream.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        }catch (NullPointerException e){
           return -1;
        }
            //if loaded list is empty
            if (loadedMedia.isEmpty()) {
                return -1;
            } else {
                return 0;
            }

    }
    //take Media Object off current library list
    private void destroyMediaObject() throws IOException, IllegalAccessException {
        long id;
        Scanner scanIn= new Scanner(System.in);
        System.out.print("Please Enter the Id to destroy");
        id= scanIn.nextLong();
        for (int i =0;i<currentMedia.size();i++) {

            if(currentMedia.get(i).getId()==id){
                //has to be checked out to be checked in
                try {
                    System.out.println(currentMedia.get(i).toString());
                    //-1 for array starting at 0
                    currentMedia.remove(i);
                    System.out.println("Media destroyed locally");
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Not the correct entry");
                }
                }
            }
        showMainMenu();
    }

    //create media
    private void createMediaMenu() throws IOException, IllegalAccessException {
        Scanner scanIn= new Scanner(System.in);

        System.out.println("Welcome to Media Rental System");
        Media media=null;
        String title;
        double length;

        String genre;
        System.out.println("Please enter title you want to add");
            title= bReader.readLine();
        System.out.println("Please enter length in minutes");
            length= scanIn.nextDouble();
        System.out.println("Please enter genre");
        genre= scanIn.next();
        System.out.println("Is this a");
        System.out.println("1:Music Cd, \n" +
        "2:DVD,\n" + " or 3:Ebook\n"
                + "Enter your selection :");
        switch (scanIn.nextInt()){
            case 1:
                media= new MusicCD(title,length,genre);
                media.setType("MusicCd");
                break;
            case 2:
                media= new MovieDVD(title,length,genre);
                media.setType("MovieDVD");
                break;
            case 3:
                media= new Media(title,length,genre);
                media.setType("EBook");
                break;





        }
        //if currentList valid and doesnt contain object we are about to create
        if(currentMedia!=null||!currentMedia.contains(media)){

            //setting media into list
            media.setId(idCounter);
            currentMedia.add(media);
            idCounter++;


        }
        showMainMenu();





    }

    private void loadMediaMenu() throws IOException, IllegalAccessException {
        scanIn= new Scanner(System.in);
        System.out.println("Enter path (directory) where to load from:");
        loadDir=scanIn.next();
        if(loadFiles(loadDir)==0){
            loadedMedia.forEach(media -> {
                System.out.println(media.toString());
            });
        }
        showMainMenu();
    }

}
