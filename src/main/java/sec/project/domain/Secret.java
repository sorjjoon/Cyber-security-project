package sec.project.domain;


public class Secret {

    public String content;

    public Secret() {
        
        super();
    }

    public Secret(String name) {
        this();
        this.content = name;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.content = name;
    }


}
