package boxuegu.com.boxuegu.bean;
public class ExercisesBean {
    private int id;
    private String title;
    private String content;
    private int background;
    public ExercisesBean() {
    }
    public ExercisesBean(int id, String title, String content, int background) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.background = background;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public int getBackground() {
        return background;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setBackground(int background) {
        this.background = background;
    }
}
