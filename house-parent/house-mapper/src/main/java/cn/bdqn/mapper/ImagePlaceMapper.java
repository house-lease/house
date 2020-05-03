package cn.bdqn.mapper;

import cn.bdqn.domain.ImagePlace;

public interface ImagePlaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImagePlace record);

    int insertSelective(ImagePlace record);

    ImagePlace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImagePlace record);

    int updateByPrimaryKey(ImagePlace record);
}