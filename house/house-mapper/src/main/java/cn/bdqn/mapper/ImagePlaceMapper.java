package cn.bdqn.mapper;

import cn.bdqn.domain.ImagePlace;

import java.util.List;

public interface ImagePlaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImagePlace record);

    int insertSelective(ImagePlace record);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    ImagePlace selectByPrimaryKey(Integer id);

    /**
     * 查询全部房屋图片位置信息
     * @return
     */
    public List<ImagePlace> selectAll();

    int updateByPrimaryKeySelective(ImagePlace record);

    int updateByPrimaryKey(ImagePlace record);
}