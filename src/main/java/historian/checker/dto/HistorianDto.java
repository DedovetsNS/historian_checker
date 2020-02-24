package historian.checker.dto;

import com.fasterxml.jackson.annotation.JsonView;
import historian.checker.dto.groups.Add;
import historian.checker.dto.groups.Details;
import historian.checker.dto.groups.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorianDto {

    @NotNull(groups = Update.class)
    @JsonView(Details.class)
    @Null(groups = {Add.class})
    private Long id;

    @NotNull(groups = Update.class)
    @JsonView(Details.class)
    @NotNull(groups = {Add.class})
    private String firstName;

    @NotNull(groups = Update.class)
    @JsonView(Details.class)
    @NotNull(groups = {Add.class})
    private String lastName;

    @NotNull(groups = Update.class)
    @Email(groups = {Add.class})
    @JsonView(Details.class)
    @NotNull(groups = {Add.class})
    private String email;

    @NotNull(groups = Update.class)
    @JsonView(Details.class)
    @NotNull(groups = {Add.class})
    private String phone;

    @NotNull(groups = Update.class)
    @JsonView(Details.class)
    @NotNull(groups = {Add.class})
    private String address;

    @NotNull(groups = Update.class)
    @JsonView(Details.class)
    @NotNull(groups = {Add.class})
    private Boolean specificLibraryAccess;
}
