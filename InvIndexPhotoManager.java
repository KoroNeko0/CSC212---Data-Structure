public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> invIndex;
    private LinkedList<Photo> photosList;

    // Constructor
    public InvIndexPhotoManager(){
        invIndex = new BST<LinkedList<Photo>>();
        photosList = new LinkedList<Photo>();
    }

    // Add a photo
    public void addPhoto(Photo p){
        if(isPhotoExist(p)) //here is to reject any duplication when addition
            System.out.println("\nAdd photo is rejected, Given Photo is Duplicated");
        else{
            photosList.insert(p);
            LinkedList<String>tagsList = p.getTags();

            tagsList.findFirst();
            while(!tagsList.last()){
                String tag1 =  tagsList.retrieve();
                boolean found = invIndex.findKey(tag1);
                if(found){

                LinkedList<Photo> photo1 = invIndex.retrieve();
                photo1.insert(p);

                }
                else {
                    LinkedList<Photo> photo1 = invIndex.retrieve();
                    photo1.insert(p);
                    invIndex.insert(tag1 , photo1);
                }

            }


        }

    }

    // Delete a photo
    public void deletePhoto(String path){

    }


    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos(){
        return invIndex;
    }



    public boolean isPhotoExist(Photo p){

        if(photosList.empty())
            return false;

        photosList.findFirst();

        while(!photosList.last()){

            if(photosList.retrieve().getPath().equals(p.getPath()))//means photo exists in the List
                return true;

            photosList.findNext();
        }
        if(photosList.retrieve().getPath().equals(p.getPath()) )// for the last photo in the List
            return true;


        return false;


    }//end method



}//end class
