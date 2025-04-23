public class InvAlbum {
    private String name;
    private String condition;
    private InvIndexPhotoManager invmanager;
    private int NbComps;

    public InvAlbum(String name, String condition, InvIndexPhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.invmanager = manager;
        this.NbComps = 0;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public InvIndexPhotoManager getManager() {
        return invmanager;
    }

    public int getNbComps() {
        return NbComps;
    }

    public LinkedList<Photo> getPhotos() {
        BST<LinkedList<Photo>> photosBST = invmanager.getPhotos();
        LinkedList<Photo> Rphotos = new LinkedList<Photo>();
        NbComps = 0;

        String[] tags;
        if (!condition.equals("")) {
            tags = condition.split("\\s*AND\\s*");
        } else {
            return getAllPhotos(photosBST); // No condition → retrieve all photos
        }

        for (int i = 0; i < tags.length; i++) {
            if (photosBST.findKey(tags[i])) {
                LinkedList<Photo> currentList = photosBST.retrieve();

                if (i == 0) {
                    currentList.findFirst();
                    while (!currentList.last()) {
                        Rphotos.insert(currentList.retrieve());
                        currentList.findNext();
                    }
                    Rphotos.insert(currentList.retrieve()); // Handle last element
                } else {
                    Rphotos = intersectPhotoLists(Rphotos, currentList);
                }
            } else {
                return new LinkedList<Photo>(); // Tag not found → return empty list
            }
        }

        return Rphotos;
    }

    private LinkedList<Photo> intersectPhotoLists(LinkedList<Photo> list1, LinkedList<Photo> list2) {
        LinkedList<Photo> result = new LinkedList<Photo>();

        if (!list2.empty()) {
            list2.findFirst();
            while (!list2.last()) {
                Photo p2 = list2.retrieve();

                if (!list1.empty()) {
                    list1.findFirst();
                    boolean found = false;

                    while (!list1.last() && !found) {
                        NbComps++;
                        if (p2.getPath().equalsIgnoreCase(list1.retrieve().getPath())) {
                            found = true;
                        }
                        list1.findNext();
                    }

                    NbComps++;
                    if (!found && p2.getPath().equalsIgnoreCase(list1.retrieve().getPath())) {
                        found = true;
                    }

                    if (found) {
                        result.insert(p2);
                    }
                }

                list2.findNext();
            }

            // Handle last element in list2
            Photo p2 = list2.retrieve();
            if (!list1.empty()) {
                list1.findFirst();
                boolean found = false;

                while (!list1.last() && !found) {
                    NbComps++;
                    if (p2.getPath().equalsIgnoreCase(list1.retrieve().getPath())) {
                        found = true;
                    }
                    list1.findNext();
                }

                NbComps++;
                if (!found && p2.getPath().equalsIgnoreCase(list1.retrieve().getPath())) {
                    found = true;
                }

                if (found) {
                    result.insert(p2);
                }
            }
        }

        return result;
    }

    private LinkedList<Photo> getAllPhotos(BST<LinkedList<Photo>> tree) {
        LinkedList<Photo> allPhotos = new LinkedList<Photo>();
        traverse(tree.getRoot(), allPhotos);
        return allPhotos;
    }

    private boolean containsPhoto(LinkedList<Photo> list, Photo p) {
        if (list.empty()) return false;

        list.findFirst();
        while (!list.last()) {
            if (p.getPath().equalsIgnoreCase(list.retrieve().getPath()))
                return true;
            list.findNext();
        }

        return p.getPath().equalsIgnoreCase(list.retrieve().getPath());
    }

    private void traverse(BSTNode<LinkedList<Photo>> node, LinkedList<Photo> result) {
        if (node == null) return;

        traverse(node.left, result);

        LinkedList<Photo> data = node.data;
        if (!data.empty()) {
            data.findFirst();
            while (!data.last()) {
                Photo p = data.retrieve();
                if (!containsPhoto(result, p)) {
                    result.insert(p);
                }
                data.findNext();
            }

            // Handle last element in the list
            Photo last = data.retrieve();
            if (!containsPhoto(result, last)) {
                result.insert(last);
            }
        }

        traverse(node.right, result);
    }


}
