package edu.badpals.model;

import edu.badpals.enums.TypeEnum;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idcard")
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Transient
    private TypeEnum type;

    @OneToOne(mappedBy = "card")
    private Student student;

    public Card() {
    }

    public Card(String code, TypeEnum type, Student student) {
        this.code = code;
        this.type = type;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", student=" + student +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) &&
                Objects.equals(code, card.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}
