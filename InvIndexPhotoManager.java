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
        if(isPhotoExist(p)) { //here is to reject any duplication when addition
            System.out.println("\nAdd photo is rejected, Given Photo is Duplicated");
        }
        else{
            photosList.insert(p);

            LinkedList<String>tagsList = p.getTags();
            tagsList.findFirst();
            while(true){

                String tag1 =  tagsList.retrieve();
                boolean isFound = invIndex.findKey(tag1);
                if(!isFound){
                    LinkedList<Photo> PList = new LinkedList<Photo>();
                    PList.insert(p);
                      invIndex.insert(tag1 , PList);
                }
                else
                    invIndex.retrieve().insert(p);



                if(!tagsList.last()){
                    tagsList.findNext();
                }
                else
                    break;


            }//end while
        }//end outer else
    }

    // Delete a photo
    public void deletePhoto(String path){

        if(photosList.empty()) {
            System.out.println("\nEmpty Photo List\n");
            return;
        }
            LinkedList<String> tagsList = null;
            photosList.findFirst();
        while (true) {

            if (photosList.retrieve().getPath().equals(path)) {
                tagsList = photosList.retrieve().getTags();
                photosList.remove();
                break;
            }

            if (photosList.last())
                break;
            else
                photosList.findNext();
        }



        if(tagsList == null) {
            System.out.println("\nRemoved is failed , Path: \"" + path + "\" is Invalid/Not found\n");
            return;
        }

        Photo photo1 = new Photo(path,tagsList);

        tagsList.findFirst();
        boolean isFound;
        while(!tagsList.last()){
            isFound = invIndex.findKey(tagsList.retrieve());
            if(isFound) {
                deleteInnerPhotoListNode(invIndex.retrieve() , photo1);
                if (invIndex.retrieve().empty())//if the inner Linked list of the Node is actually empty (doesn't have any photos) then it will be removes from the BST
                    invIndex.removeKey(tagsList.retrieve());
            }
            tagsList.findNext();
        }
        isFound = invIndex.findKey(tagsList.retrieve());
        if(isFound) {
            deleteInnerPhotoListNode(invIndex.retrieve() , photo1);
            if (invIndex.retrieve().empty())
                invIndex.removeKey(tagsList.retrieve());
        }
    }


    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos(){
        return invIndex;
    }



    private boolean isPhotoExist(Photo p){

        if(photosList.empty())
            return false;

        photosList.findFirst();

        while(!photosList.last()){

            if(photosList.retrieve().getPath().equals(p.getPath()))//means photo exists in the List
                return true;

            photosList.findNext();
        }
        if(photosList.retrieve().getPath().equals(p.getPath()) )//it's for the last photo in the List
            return true;


        return false;


    }//end method

    private void deleteInnerPhotoListNode(LinkedList<Photo> innerList, Photo p) {
        if (innerList.empty()) return;

        innerList.findFirst();

        while(true) {
            if (innerList.retrieve().getPath().equals(p.getPath())) {
                innerList.remove();
                break;
            }

            if (innerList.last())
                break;
            innerList.findNext();
        }
    }

}//end class
