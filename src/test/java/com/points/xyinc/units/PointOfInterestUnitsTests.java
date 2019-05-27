package com.points.xyinc.units;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.points.xyinc.domain.PointOfInterest;
import com.points.xyinc.repositories.PointOfInterestRepository;
import com.points.xyinc.services.PointOfInterestService;
import com.points.xyinc.services.exceptions.InvalidParametersException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointOfInterestUnitsTests {

	@Autowired
	private PointOfInterestService pointService;
	
	@Autowired
	private PointOfInterestRepository pointRepository;
	
	@Before
	public void setUp() {

	}
	
	@Test
	public void findAllPointsTest() {
		List<PointOfInterest> list = pointService.findAll();
		
		Assert.assertNotNull("Not null ok", list);
		Assert.assertEquals("Expected size ok", 7, list.size());
	}
	
    @Test
    public void insertPointTest() {

    	PointOfInterest entity = new PointOfInterest();
    	entity.setName("point");
    	entity.setCoordinateX(50);
    	entity.setCoordinateY(20);

    	PointOfInterest point = pointService.insert(entity);

        Assert.assertNotNull("Not null ok", point);
        Assert.assertNotNull("Id not null ok",point.getId());
        Assert.assertEquals("Name attribute match", "point", point.getName());
        
        pointRepository.deleteById(point.getId());
    }
    
    @Test(expected = InvalidParametersException.class)
    public void errorInsertCoordinateXTest() throws Exception{

    	PointOfInterest entity = new PointOfInterest();
    	entity.setName("point");
    	entity.setCoordinateY(50);

    	pointService.insert(entity);
    }
    
    @Test(expected = InvalidParametersException.class)
    public void errorInsertCoordinateYTest() throws Exception{

    	PointOfInterest entity = new PointOfInterest();
    	entity.setName("point");
    	entity.setCoordinateX(50);

    	pointService.insert(entity);
    }
    
    @Test(expected = InvalidParametersException.class)
    public void errorInsertNameTest() throws Exception{

    	PointOfInterest entity = new PointOfInterest();
    	entity.setCoordinateY(10);
    	entity.setCoordinateX(50);

    	pointService.insert(entity);
    }
    
    @Test
    public void findPointsByCoordinatesTest(){
		List<PointOfInterest> list = pointService.findByCoordenates(20, 10, 10);
		
		Assert.assertNotNull("Not null ok", list);
		Assert.assertEquals("Expected size ok", 4, list.size());
    }
}
