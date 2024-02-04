package br.com.martins.criptografia.entity;

import br.com.martins.criptografia.component.EncryptComponent;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Convert(converter = EncryptComponent.class)
    private String userDocument;
    @NonNull
    @Convert(converter = EncryptComponent.class)
    private String creditCardToken;
    @NonNull
    private Double value;
}
