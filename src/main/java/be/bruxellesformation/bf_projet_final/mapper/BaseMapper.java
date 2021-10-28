package be.bruxellesformation.bf_projet_final.mapper;

public interface BaseMapper <TDTO, TFORM, TENTITY>{
    TENTITY toEntity(TDTO dto);
    TDTO toDto(TENTITY entity);
    TENTITY fromFormToEntity(TFORM form);
}
