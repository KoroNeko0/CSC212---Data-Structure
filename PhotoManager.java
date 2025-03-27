public class PhotoManager {

     private LinkedList<Photo> photosList;

    //Constructor
    public PhotoManager(){

    photosList = new LinkedList<Photo>();
    }
    // Return all managed photos
    public LinkedList<Photo> getPhotos(){
    return photosList;
    }
    // Add a photo
    public void addPhoto(Photo p){

        if(photosList.empty())
            photosList.insert(p);
        else{
            boolean isExist = false; //to prevent photo duplication in the List, this Variable checks if photo exists or not
            photosList.findFirst();

            while(!photosList.last()){

                if(photosList.retrieve().getPath().equals(p.getPath()))//means photo exists in the List
                    isExist = true;

                photosList.findNext();
            }
            if(photosList.retrieve().getPath().equals(p.getPath()) )// for the last photo in the List
                isExist = true;

            if(isExist)
                System.out.println("\nAdd photo is rejected, Given Photo is Duplicated");
            else
                photosList.insert(p);
        }
    }

    // Delete a photo
    public void deletePhoto(String path){

        if(photosList.empty()) {
            System.out.println("\nEmpty Photo List\n");
        }
        else{
            boolean isFound = false;
            photosList.findFirst();

            while(!photosList.last()) {

                if (photosList.retrieve().getPath().equals(path)){
                    isFound = true;
                    break;
                }//end if
                photosList.findNext();

            }//end while

            if (photosList.retrieve().getPath().equals(path))
                isFound = true;

            if(isFound) {
                photosList.remove();
                System.out.println("\nPhoto is removed Successfully\n");
            }else{
                System.out.println("\nRemoved is failed , Path: \""+ path +"\" is Invalid\n");
            }
        }//end else
    }
}//end class
