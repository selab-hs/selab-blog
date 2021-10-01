package kr.ac.hs.selab.member.domain.vo;

import kr.ac.hs.selab.exception.ErrorMessage;
import kr.ac.hs.selab.exception.InvalidInputException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class StudentId {
    @Transient
    private static final String STUDENT_ID_REGEX = "^[0-9]{4}58[0-9]{3}$";

    @Column(name = "member_student_id")
    private String studentId;

    public StudentId(String studentId) {
        validate(studentId);
        this.studentId = studentId;
    }

    public void validate(String studentId) {
        if (!Pattern.matches(STUDENT_ID_REGEX, studentId)) {
            throw new InvalidInputException(ErrorMessage.INVALID_CLASS_NUMBER);
        }
    }

    public String getStudentId() {
        return studentId;
    }
}