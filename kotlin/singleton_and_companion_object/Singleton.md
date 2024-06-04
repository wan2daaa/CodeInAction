# 싱글톤

- 싱글톤 패턴은 클래스의 인스턴스를 **하나의 단일 인스턴스로 제한**하는 디자인 패턴
- 싱글톤 패턴을 구현할 때는 몇가지 제약사항을 통해 구현한다
  - 직접 인스턴스화 하지 못하도록 **생성자를 private**로 숨긴다
  - **getInstance()** 라는 클래스의 단일 인스턴스를 반환하는 static 메서드를 제공한다
  - 멀티-스레드 환경에서도 **안전하게 유일한 인스턴스를 반환**한다.
- 다양한 구현방법들
  - DCL(Double Check Locking)
    - JVM 에선 거의 사용 X (메모리 이슈)
  - Enum 싱글톤 (Effective Java)
    - 하지만 실무에서는 많이 활용 안함 
  - 이른 초기화 (Eager)
  - 지연 초기화 (Lazy)

## 자바에서 많이 쓰이는 구현방식 
- 이른 초기화 

```java
public class Java_Singleton {

  private static final Java_Singleton INSTANCE = new Java_Singleton();
  
  private Java_Singleton {}

  public Java_Singleton getInstance() {
    return INSTANCE;
  }
}
```
- 지연 초기화 

```java
public class Java_Singleton {
  
  private Java_Singleton {}

  public Java_Singleton getInstance() {
    return LazyHolder.INSTANCE;
  }
  
  private static class LazyHolder {
    private static final Java_Singleton INSTANCE = new Java_Singleton();
  }
}
```


