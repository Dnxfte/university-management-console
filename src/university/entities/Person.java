package university.entities;

public class Person {
    private int id;
    private String fullName;
    private String email;

    public Person(int id, String fullName, String email) {
        setId(id);
        setFullName(fullName);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID має бути більше 0.");
        }

        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (isBlank(fullName)) {
            throw new IllegalArgumentException("ПІБ не може бути порожнім.");
        }

        this.fullName = fullName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Неправильний формат email.");
        }

        this.email = email.trim();
    }

    protected String getPersonInfo() {
        return "ID: " + id + ", ПІБ: " + fullName + ", email: " + email;
    }

    protected boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        if (isBlank(email)) {
            return false;
        }

        String trimmedEmail = email.trim();
        int atIndex = trimmedEmail.indexOf("@");
        int dotIndex = trimmedEmail.lastIndexOf(".");

        return atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < trimmedEmail.length() - 1;
    }

    @Override
    public String toString() {
        return getPersonInfo();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Person person = (Person) object;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
