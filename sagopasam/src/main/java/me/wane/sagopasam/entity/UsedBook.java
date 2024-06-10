package me.wane.sagopasam.entity;

import jakarta.persistence.*;
import lombok.*;
import me.wane.sagopasam.vo.BookStatus;
import me.wane.sagopasam.vo.College;
import me.wane.sagopasam.vo.Department;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UsedBook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private LocalDateTime tradeAvailableDatetime;

    private String publisher;

    @Enumerated(EnumType.STRING)
    private College college;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Setter
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ColumnDefault("false")
    private Boolean isUnderlinedOrWrite;

    @ColumnDefault("false")
    private Boolean isDiscolorationAndDamage;

    @ColumnDefault("false")
    private Boolean isCoverDamaged;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Member buyerMember;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Member sellerMember;

    @ColumnDefault("false")
    private Boolean isDeleted;

    private String mainImageUrl;

//    @Setter
//    @OneToMany(mappedBy = "usedBook", cascade = CascadeType.ALL)
//    private List<BookImage> bookImageList = new ArrayList<>();

    public void setIsDeletedTrue() {
        isDeleted = true;
    }

    @Builder
    public UsedBook(Long id, String name, Integer price, LocalDateTime tradeAvailableDatetime, String publisher, College college, Department department, BookStatus bookStatus, Boolean isUnderlinedOrWrite, Boolean isDiscolorationAndDamage, Boolean isCoverDamaged,  Member sellerMember, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tradeAvailableDatetime = tradeAvailableDatetime;
        this.publisher = publisher;
        this.college = college;
        this.department = department;
        this.bookStatus = bookStatus;
        this.isUnderlinedOrWrite = isUnderlinedOrWrite;
        this.isDiscolorationAndDamage = isDiscolorationAndDamage;
        this.isCoverDamaged = isCoverDamaged;
        this.sellerMember = sellerMember;
        this.isDeleted = isDeleted;
    }

}
