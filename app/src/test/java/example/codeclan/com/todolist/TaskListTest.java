package example.codeclan.com.todolist;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 20/04/2017.
 */

public class TaskListTest {

        private TaskList taskList;


    @Before
        public void before(){
            taskList = new TaskList();
        }

        @Test
        public void canAddGamesResult(){
            taskList.addToList(new Task("description","long description",PriorityLevel.HIGH,1));
            assertEquals(1,taskList.sizeOfList());
        }


        @Test
        public void canAddMultipleGamesResults(){
            taskList.addToList(new Task("description","long description",PriorityLevel.HIGH,1));
            taskList.addToList(new Task("description","long description",PriorityLevel.LOW,1));
            taskList.addToList(new Task("description","long description",PriorityLevel.MEDIUM,1));
            assertEquals(3,taskList.sizeOfList());
        }

        @Test
        public void canGetUserChoiceFromList(){
            taskList.addToList(new Task("description 1","long description",PriorityLevel.HIGH,1));
            taskList.addToList(new Task("description 2","longer description",PriorityLevel.LOW,1));
            String shortDescription1 =  taskList.entry(1).getShortDescription();
            assertEquals("description 2",shortDescription1);
        }
}
