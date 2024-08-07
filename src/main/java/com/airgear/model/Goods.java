package com.airgear.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "usersAddedToFavorite", "goodsViews"})
@ToString(exclude = {"user", "usersAddedToFavorite", "goodsViews"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 255, message = "Name length must be between 3 and 255 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 1000, message = "Description length must be between 10 and 1000 characters")
    private String description;

    @Embedded
    @NotNull(message = "price cannot be null")
    private Price price;

    @Embedded
    private WeekendsPrice weekendsPrice;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Embedded
    private Deposit deposit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    private GoodsVerificationStatus verificationStatus;

    @Enumerated(EnumType.STRING)
    private GoodsStatus status;

    @Pattern(regexp = "^\\+\\d{1,3}\\s?\\(?\\d{1,4}\\)?[\\s.-]?\\d{1,4}[\\s.-]?\\d{1,4}$", message = "The phone number must be in the format +380XXXXXXXXX")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "last_modified")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OffsetDateTime lastModified;

    @Column(name = "deleted_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OffsetDateTime deletedAt;

    @OneToMany(mappedBy = "goods")
    private List<Complaint> complaints;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_FAVORITE_GOODS",
            joinColumns = {
                    @JoinColumn(name = "GOODS_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "USER_ID")})
    private Set<User> usersAddedToFavorite;

    @JsonIgnore
    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GoodsView> goodsViews;

    @JsonIgnore
    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TopGoodsPlacement> topGoodsPlacements;

    @Enumerated(EnumType.STRING)
    private GoodsCondition goodsCondition;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    private List<GoodsImages> images = new ArrayList<>();

    @Column(name = "source_url")
    private String sourceUrl;
}
