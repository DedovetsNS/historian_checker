package historian.checker.transformer;

import historian.checker.dto.HistorianDto;
import historian.checker.model.Historian;

import java.util.Set;

public interface Transformer {
    Historian toEntity(HistorianDto historianDto);

    HistorianDto toDto(Historian historian);

    Set<HistorianDto> toDto(Set<Historian> historians);
}
