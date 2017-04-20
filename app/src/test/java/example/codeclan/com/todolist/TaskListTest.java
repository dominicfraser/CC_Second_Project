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
            taskList.addToList(new Task(4,"description","long description",PriorityLevel.HIGH,false));
            assertEquals(1,taskList.sizeOfList());
        }


        @Test
        public void canAddMultipleGamesResults(){
            taskList.addToList(new Task(1,"description","long description",PriorityLevel.HIGH,false));
            taskList.addToList(new Task(2,"description","long description",PriorityLevel.LOW,true));
            taskList.addToList(new Task(3,"description","long description",PriorityLevel.MEDIUM,true));
            assertEquals(3,taskList.sizeOfList());
        }

        @Test
        public void canGetUserChoiceFromList(){
            taskList.addToList(new Task(1,"description 1","long description",PriorityLevel.HIGH,false));
            taskList.addToList(new Task(2,"description 2","longer description",PriorityLevel.LOW,false));
            String shortDescription1 =  taskList.entry(1).getShortDescription();
            assertEquals("description 2",shortDescription1);
        }
}
