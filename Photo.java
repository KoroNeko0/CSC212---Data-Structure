public class Photo {
    private String path;
    private LinkedList<String> tags;
    // Constructor
    public Photo(String path, LinkedList<String> tags){
        this.path=path;
        this.tags=tags;
    }
    // Return the full file name (the path) of the photo. A photo is uniquely identified by
    //its path.
    public String getPath(){
        return path;
    }
    // Return all tags associated with the photo
    public LinkedList<String> getTags(){
        return tags;
    }

    public void DisplayAllTags(){//this method is to show all tags of a photo

        tags.findFirst();

        while(!tags.last()){
            System.out.print(tags.retrieve() +" , ");
            tags.findNext();
        }
        System.out.print(tags.retrieve() +"\n==============================\n");

    }
}//end class
