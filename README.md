### SELAB
> 한신대학교 SELAB 홈페이지



### Project Layer
- `domain명`
  + application
  + infrastructure
  + presentation
  + dto
  + domain
    + vo
  + converter


- Entity는 Service에 고립시켜서 사용
  + Dto를 매개변수로 받고, return함
  + Controller는 Entity를 모름
- converter를 통해 `DTO->ENTITY`로 그리고 `Entity -> DTO`로 변환시킴
