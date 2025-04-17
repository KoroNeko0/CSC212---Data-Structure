public class Test {
    public static void main(String[] args) {

        PhotoManager manager = new PhotoManager();

        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        manager.addPhoto(photo1);
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo2);
        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        manager.addPhoto(photo3);
        Photo photo4 = new Photo("black-butterfly.jpg" , toTagsLinkedList("insect, butterfly, flower, black"));
        manager.addPhoto(photo4);
        Photo photo5 = new Photo("fox.jpg" , toTagsLinkedList("animal, fox, forest, grass"));
        manager.addPhoto(photo5);
        Photo photo6 = new Photo("panda.jpg" , toTagsLinkedList("animal, bear, panda, grass"));
        manager.addPhoto(photo6);
        Photo photo7 = new Photo("wolf.jpg" , toTagsLinkedList("animal, wolf, mountain, sky, snow, cloud"));
        manager.addPhoto(photo7);
        Photo photo8 = new Photo("raccoon.jpg" , toTagsLinkedList("animal, raccoon, log, snow"));
        manager.addPhoto(photo8);

        displayAllPhotos(manager);

        System.out.println("After Deleting hedgehog.jpg");
        manager.deletePhoto("hedgehog.jpg");
        displayAllPhotos(manager);

        Album album1 = new Album("Album1", "bear", manager);
        Album album2 = new Album("Album2", "animal AND grass", manager);

        System.out.println("Get photo1 path and tags:");
        System.out.println("photo1 path: " + photo1.getPath());

        //You can get the list of tags of photo1 by calling photo1.getTags().
        LinkedList<String> Tags = photo1.getTags();
        //You can write a method that prints the list of tags of photo1.
        photo1.DisplayAllTags();

        System.out.println("Get album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());

        //You can get the list of photos in album2 by calling album2.getPhotos().
        LinkedList<Photo> photos = album2.getPhotos();
        //You can write a method that prints the list of photos in album2.
        PrintAllPhotos(photos);// photo path in album2

        System.out.println("Delete the photo ’bear.jpg’:");
        manager.deletePhoto("bear.jpg");
        displayAllPhotos(manager);

    }//end main

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();

        String[] tagsArray = tags.split("\\s*,\\s*");

        for (int i = 0; i < tagsArray.length; i++) {
            result.insert(tagsArray[i]);
        }//end for

        return result;
    }

    public static void displayAllPhotos(PhotoManager pm) { //Displays all Photos in a specific Photo-Manager
        LinkedList<Photo> allPhotos = pm.getPhotos();

        if (!allPhotos.empty()) {

            allPhotos.findFirst();
            int photoNum = 0;

            while (!allPhotos.last()) {
                System.out.println("\nPhoto No." + (photoNum + 1) + ":");
                System.out.println("Path: " + allPhotos.retrieve().getPath());
                System.out.print("Tags: ");
                allPhotos.retrieve().DisplayAllTags();

                photoNum++;
                allPhotos.findNext();
            }//end while

            System.out.println("\nPhoto No." + (photoNum + 1) + ":");
            System.out.println("Path: " + allPhotos.retrieve().getPath());
            System.out.print("Tags: ");
            allPhotos.retrieve().DisplayAllTags();

        }//end if
    }


    private static void PrintAllPhotos(LinkedList<Photo> photos) {

        if (!photos.empty()) {

            photos.findFirst();

            while (!photos.last()) {
                System.out.println("Path: " + photos.retrieve().getPath());
                photos.findNext();
            }//end while
            System.out.println("Path: " + photos.retrieve().getPath());

        }//end if
        else
            System.out.println("\nEmpty List\n");
    }

}//end class
