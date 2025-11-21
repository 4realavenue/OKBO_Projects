package com.okbo_projects.common.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "follows")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "follower_id", nullable = false)
//    private User follower;
    private Long follower;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "following_id", nullable = false)
//    private User following;
    private Long following;

//    public Follow(User follower, User following) {
//        this.follower = follower;
//        this.following = following;
//    }
    public Follow(Long follower, Long following) {
        this.follower = follower;
        this.following = following;
    }
}
