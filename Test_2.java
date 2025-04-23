public class Test_2 {
public static void main (String [] args) {
    InvIndexPhotoManager manager = new InvIndexPhotoManager();

    Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
   manager.addPhoto(photo1);
   Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
   manager.addPhoto(photo2);
    Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
    manager.addPhoto(photo3);
    Photo photo4 = new Photo("black-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, black"));
    manager.addPhoto(photo4);
    Photo photo5 = new Photo("fox.jpg", toTagsLinkedList("animal, fox, forest, grass"));
    manager.addPhoto(photo5);
    Photo photo6 = new Photo("panda.jpg", toTagsLinkedList("animal, bear, panda, grass"));
    manager.addPhoto(photo6);
    Photo photo7 = new Photo("wolf.jpg", toTagsLinkedList("animal, wolf, mountain, sky, snow, cloud"));
    manager.addPhoto(photo7);
   Photo photo8 = new Photo("raccoon.jpg", toTagsLinkedList("animal, raccoon, log, snow"));
    manager.addPhoto(photo8);

    //testing delete method
    manager.deletePhoto("orange-butterfly.jpg");

    BST<LinkedList<Photo>> invIndex = manager.getPhotos();
    invIndex.inOrder();

    //####################################################################################################################


    InvAlbum album1 = new InvAlbum("Album1", "bear", manager);
    InvAlbum album2 = new InvAlbum("Album2", "animal AND grass", manager);
    InvAlbum album3 = new InvAlbum("Album3", "", manager);

    System.out.println("\nAlbum1 (condition: bear):");
    printLLPhoto(album1.getPhotos());
    System.out.println("Comparisons: " + album1.getNbComps());

    System.out.println("\nAlbum2 (condition: animal AND grass):");
    printLLPhoto(album2.getPhotos());
    System.out.println("Comparisons: " + album2.getNbComps());

    System.out.println("\nAlbum3 (no condition):");
    printLLPhoto(album3.getPhotos());
    System.out.println("Comparisons: " + album3.getNbComps());

    System.out.println("\nDeleting bear.jpg...");
    manager.deletePhoto("bear.jpg");

    System.out.println("\nAlbum2 after deleting bear.jpg:");
    printLLPhoto(album2.getPhotos());
    System.out.println("Comparisons: " + album2.getNbComps());




}//end main

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();

        String[] tagsArray = tags.split("\\s*,\\s*");

        for (int i = 0; i < tagsArray.length; i++) {
            result.insert(tagsArray[i]);
        }//end for

        return result;
    }

    private static void printLLPhoto(LinkedList<Photo> list) {
        if (list.empty()) return;
        list.findFirst();
        while (!list.last()) {
            System.out.println(list.retrieve().getPath());
            list.findNext();
        }
        System.out.println(list.retrieve().getPath());
    }
}//end class
