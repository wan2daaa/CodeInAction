# 지연초기화 

- 지연초기화는 대상에 대한 초기화를 미뤘다가 실제 사용시점에 초기화 하는 기법 
- 초기화 과정에서 자원을 많이 쓰거나 오버헤드가 발생할 경우 지연 초기화를 사용하는게 유리할 수 있다.
- 지연초기화는 많은 상황에서 쓰이고 있다.
  - 웹페이지에서 특정 스크롤에 도달했을때 컨텐츠를 보여주는 무한 스크롤
  - 싱글톤 패턴의 지연 초기화
  - JPA 의 엔티티 LazyLoading

```java
public class Java_Singleton {
  private Java_Singleton{}
  
  public Java_Singleton getInstance(){
    return LazyHolder.INSTANCE;    
  }
  
  private static class LazyHolder {
    private static final Java_Singleton INSTANCE = new Java_Singleton();
  }
}

@OneToMany(fetch = FetchType.LAZY)
```



