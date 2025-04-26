public class Album {

    private String Name;
    private String Condition;
    private PhotoManager Manager;
    private int NumComps;

    public Album(String name, String condition, PhotoManager manager) {
        Name = name;
        Condition = condition;
        Manager = manager;
        NumComps = 0;

    }

    // Return the name of the album
    public String getName() {
        return Name;
    }

    // Return the condition associated with the album
    public String getCondition() {
        return Condition;
    }

    // Return the manager
    public PhotoManager getManager() {
        return Manager;
    }

    // Return all photos that satisfy the album condition
    public LinkedList<Photo> getPhotos() {

        LinkedList<Photo> Rphotos = new LinkedList<Photo>();
        
            LinkedList<Photo> photos1 = Manager.getPhotos();
            if (!photos1.empty()) {
                photos1.findFirst();
                while (!photos1.last()) {
                    Rphotos.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
                    photos1.findNext();
                }
                Rphotos.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
            }
        

        NumComps = 0;
        if (this.Condition.compareTo("") != 0) {
            String[] Array = Condition.split(" AND ");

            Rphotos.findFirst();
            while (!Rphotos.last()) {
                Photo photo = Rphotos.retrieve();
                if (!isTagAvailable(photo.getTags(), Array))
                    Rphotos.remove();
                else
                    Rphotos.findNext();
            }
            Photo photo11 = Rphotos.retrieve();
            if (!isTagAvailable(photo11.getTags(), Array))
                Rphotos.remove();
            else
                Rphotos.findNext();
        }
        return Rphotos;
    }
    

    // Return the number of tag comparisons used to find all photos of the album
    public int getNbComps() {
        return NumComps;
    }

    private boolean isTagAvailable(LinkedList<String> AllTags, String[] ArrayC) {
        if (AllTags.empty()) return false;

        for (String Tag : ArrayC) {
            boolean found = false;

            AllTags.findFirst();
            while (!AllTags.last()) {
                NumComps++;

                if (AllTags.retrieve().equalsIgnoreCase(Tag)) {
                    found = true;
                    break;
                }

                AllTags.findNext(); 
            }

            if (!found) {
                NumComps++;
                if (AllTags.retrieve().equalsIgnoreCase(Tag)) {
                    found = true;
                }
            }

            if (!found) return false;  
        }

        return true; 
    }


}


