package msg.ejb;

import javax.ejb.Stateless;

@Stateless
public class AnotherEjbImpl implements AnotherEjb {

    @Override
    public void doSomething() {
        System.out.println("do nothing");
    }
}
