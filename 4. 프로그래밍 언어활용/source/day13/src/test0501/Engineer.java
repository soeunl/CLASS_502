package test0501;

public class Engineer extends Employee {
    private String skillset;
    public String getSkillset() {
        return skillset;
    }
    public Engineer () {
        super("name");
    }
    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }
}
