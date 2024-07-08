package tagarde.core.domain.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tagarde.core.domain.auth.user.UserEntity;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@ToString
@Data
public abstract class Token {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "token", unique = true, nullable = false)
    protected String token;
    @Column(name = "revoked", nullable = false)
    protected boolean revoked;
    @Column(name = "expired", nullable = false)
    protected boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected UserEntity userEntity;



}