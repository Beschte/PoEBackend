package smartFaensterBackend.entities;

public class WindowDto {

    public long id;
    public int open;

    public WindowDto(long id, int open){
        this.id = id;
        this.open = open;
    }
    

    public WindowDto() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOpenInt() {
        return open;
    }

    public void setOpenInt(int openInt) {
        this.open = open;
    }


}
