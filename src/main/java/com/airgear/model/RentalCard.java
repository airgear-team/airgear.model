package com.airgear.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rental_card")
public class RentalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "lessor_id", nullable = false)
    private User lessor;

    @ManyToOne
    @JoinColumn(name = "renter_id", nullable = false)
    private User renter;

    @Column(name = "first_date")
    @NotNull(message = "First date cannot be null")
    private OffsetDateTime firstDate;

    @Column(name = "last_date")
    @NotNull(message = "Last date cannot be null")
    private OffsetDateTime lastDate;

    @Enumerated(EnumType.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ChronoUnit duration;

    @Column(name = "quantity")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;

    @Column(name = "rental_price")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal rentalPrice;

    @Column(name = "fine")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal fine;

    @Column(name = "description")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @Column(name = "deleted_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OffsetDateTime deletedAt;

}