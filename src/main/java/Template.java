public class Template implements Comparable<Template> {
    private String name;
    private int val;

    public Template(String name, int val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "Template{" +
                "name='" + name + '\'' +
                ", val=" + val +
                '}';
    }

    @Override
    public int compareTo(Template template) {
        if (this.val > template.getVal()) {
            return -1;
        } else if (this.val < template.getVal()) {
            return 1;
        } else if (this.val == template.getVal()) {
            return this.getName().compareTo(template.getName());
        } else {
            return 0;
        }
    }
}
