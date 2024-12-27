package com.example.api_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EditElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edit_element_id")
    private Long editElementId;

    @Column(name = "angle", nullable = true)
    private Integer angle;

    @Column(name = "height", nullable = true)
    private Integer height;

    @Column(name = "width", nullable = true)
    private Integer width;

    @Column(name = "x_position", nullable = true)
    private Integer x;

    @Column(name = "y_position", nullable = true)
    private Integer y;

    @Column(name = "scale_x", nullable = true)
    private Float scaleX;

    @Column(name = "scale_y", nullable = true)
    private Float scaleY;

    @Column(name = "fill", nullable = true, length = 255)
    private String fill;

    @Column(name = "font_style", nullable = true, length = 255)
    private String fontStyle;

    @Column(name = "font_size", nullable = true, length = 255)
    private String fontSize;

    @Column(name = "font_weight", nullable = true, length = 255)
    private String fontWeight;

    @Column(name = "stroke_width", nullable = true, length = 255)
    private String strokeWidth;

    @Column(name = "stroke_color", nullable = true, length = 255)
    private String strokeColor;

    @Column(name = "text", nullable = true, length = 255)
    private String text;

    @Column(name = "text_decoration", nullable = true, length = 255)
    private String textDecoration;

    @Column(name = "type", nullable = true, length = 255)
    private String type;

    @Column(name = "field", nullable = true, length = 255)
    private String field;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
