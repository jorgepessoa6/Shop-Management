package resource.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final User user = new User();
    private final UUID uuid = UUID.randomUUID();
    private final UUID setUuid = UUID.randomUUID();

    public UserTest(){

    }

    @BeforeEach
    public void setup(){
        user.setId(uuid);
        user.setUsername("user");
        user.setPassword("pw");
        user.setAddress(new Address("test1", "test2", "test3"));
        user.setEmail("email@gmail.com");
        user.setTaxIdNumber("123456789");
        Set<ROLE> roles = new HashSet<>();
        roles.add(ROLE.CUSTOMER);
        user.setRoles(roles);
    }

    @Test
    void getId() {
        assertEquals(uuid,user.getId());
    }

    @Test
    void getUsername() {
        assertEquals("user",user.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("email@gmail.com",user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("pw",user.getPassword());
    }

    @Test
    void getAddress() {
        assertEquals("test1",user.getAddress().getAddress());
        assertEquals("test2",user.getAddress().getPostalCode());
        assertEquals("test3",user.getAddress().getLocation());

    }

    @Test
    void getTaxIdNumber() {
        assertEquals("123456789",user.getTaxIdNumber());
    }

    @Test
    void getRoles() {
        assertEquals(1,user.getRoles().size());
    }

    @Test
    void setId() {
        user.setId(setUuid);
        assertEquals(setUuid,user.getId());
    }

    @Test
    void setUsername() {
        user.setUsername("new");
        assertEquals("new",user.getUsername());
    }

    @Test
    void setEmail() {
        user.setEmail("email@hotmail.com");
        assertEquals("email@hotmail.com",user.getEmail());
    }

    @Test
    void setPassword() {
        user.setPassword("newPw");
        assertEquals("newPw",user.getPassword());
    }

    @Test
    void setAddress() {
        user.setAddress(new Address("newtest1", "newtest2", "newtest3"));
        assertEquals("newtest1",user.getAddress().getAddress());
        assertEquals("newtest2",user.getAddress().getPostalCode());
        assertEquals("newtest3",user.getAddress().getLocation());
    }

    @Test
    void setTaxIdNumber() {
        user.setTaxIdNumber("123456799");
        assertEquals("123456799",user.getTaxIdNumber());
    }

    @Test
    void setRoles() {
        user.getRoles().add(ROLE.PERSON_IN_CHARGE);
        assertEquals(2,user.getRoles().size());
    }
}