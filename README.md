# planetland

<img width="1042" alt="image" src="https://user-images.githubusercontent.com/125088568/225655595-980405a8-5edb-4582-a8eb-f8ea1cab0af3.png">

## 플래닛랜드에 오신것을 환영합니다!
플래닛랜드는 웹으로 즐기는 간단한 매매(우주ver) 게임입니다. <br>
행성을 사고 팔며 돈을 모아 다른 여러 행성을 소유할 수 있고 <br>
업그레이드를 통해 더 많은 돈을 얻을 수 있습니다. <br><br>

## 개발 환경
- 운영체제: macOS <br>
- IDE: IntelliJ <br>
- Java 8 <br>
- Spring boot 2.7.8 <br>
- Spring Web <br>
- Spring Data JPA <br>
- Spring Security <br>
- 데이터베이스: MySQL <br>
- Bootsrap, Thymeleaf <br><br>

## ERD 설계
<img width="380" alt="image" src="https://user-images.githubusercontent.com/125088568/225725317-4b01b3d6-7c29-477a-bf07-92396d38933d.png">
<br><br>

## 구현
<details><summary>메인 화면</summary>

![image](https://user-images.githubusercontent.com/125088568/225675128-efcb4a5a-b8c8-4a1a-a016-fbf4e5d082e7.png)
<br><br>
## <코드><br>
<details><summary>우측 상단 행성등록 링크는 관리자계정으로 로그인 시 화면에 노출</summary>

<img width="669" alt="image" src="https://user-images.githubusercontent.com/125088568/225696912-32bac414-98e8-4913-abe3-8945bbeb02be.png">
</details>

<details><summary>비로그인 유저가 마이페이지 접근 시 로그인 화면으로 전환</summary>

<img width="397" alt="image" src="https://user-images.githubusercontent.com/125088568/225697814-5c54648b-52a8-4d41-b331-ac5f96743b7c.png">
</details>

<details><summary>정렬은 기본 순, 높은 가격 순, 낮은 가격 순 3가지 구현</summary>

<img width="750" alt="image" src="https://user-images.githubusercontent.com/125088568/225698152-03142214-9091-4512-ae2c-6c988dd725f8.png"> <br>
<img width="652" alt="image" src="https://user-images.githubusercontent.com/125088568/225698521-3648ccb3-5c94-4725-99d9-4453f148fc24.png">
</details><br/>

</details>





<details><summary>로그인 화면</summary>

![image](https://user-images.githubusercontent.com/125088568/225677250-fe6c6d43-0c3e-44bd-9004-1eccfce5133f.png)
![image](https://user-images.githubusercontent.com/125088568/225677683-d4c50c1f-2d47-4081-80d2-25fd602ed241.png) <br> <br>

## <코드><br>
<details><summary>아이디 또는 비밀번호 불일치 시 확인 메시지 전달</summary>

<img width="771" alt="image" src="https://user-images.githubusercontent.com/125088568/225699707-459dc89a-9d90-4c8d-a7e5-4b4a59724dbe.png"> <br>
<img width="659" alt="image" src="https://user-images.githubusercontent.com/125088568/225699937-730a8e2f-8fef-4aed-8528-f4f758298f0d.png">
</details><br/>

</details>





<details><summary>회원가입 화면</summary>

<img width="722" alt="image" src="https://user-images.githubusercontent.com/125088568/225702838-e0c361aa-82c7-4015-a392-9c3a0ea9e9c6.png"><br><br>

## <코드><br>
<details><summary>아이디에 공백 방지 및 아이디 또는 이메일 중복 시 오류 메시지 전달</summary>

<img width="807" alt="image" src="https://user-images.githubusercontent.com/125088568/225703597-c9a2fbfd-8a6b-4b72-8ce5-2798df079802.png"><br>
<img width="791" alt="image" src="https://user-images.githubusercontent.com/125088568/225703894-08a3d7b0-b750-4c41-aced-e54a47a55cef.png">
</details><br/>

</details>





<details><summary>행성등록 화면</summary>

![image](https://user-images.githubusercontent.com/125088568/225716198-7eb5c8c1-a1a7-4fb7-818f-fceadbc06aa6.png)<br><br>

## <코드><br>
<details><summary>행성이름 중복 시 오류 메시지 전달</summary>

<img width="720" alt="image" src="https://user-images.githubusercontent.com/125088568/225719112-ded0e332-f1a0-4c31-ae95-997d9307b07d.png"><br>
<img width="732" alt="image" src="https://user-images.githubusercontent.com/125088568/225718940-ccd74db6-3672-4fe2-af87-d3fdd0db0cb8.png">
</details>

<details><summary>파일명 중복 방지 및 저장</summary>

<img width="551" alt="image" src="https://user-images.githubusercontent.com/125088568/225721612-1f8fb603-7d0c-409d-89e8-3a3c94fce6ba.png">
</details>

<details><summary>행성 이미지 미첨부 시 대체 이미지 설정</summary>

<img width="758" alt="image" src="https://user-images.githubusercontent.com/125088568/225719845-ced47c4e-b299-41da-932a-cf0ad2c6b91c.png">
</details><br/>

</details>





<details><summary>행성수정 화면</summary>

<img width="240" alt="image" src="https://user-images.githubusercontent.com/125088568/225722500-0fc26cf8-7603-4753-bba5-b3a9848f2e01.png"><br><br>

## 코드<br>

<details><summary>관리자계정 로그인 시 행성 상세정보 페이지에 수정버튼 노출</summary>

<img width="700" alt="image" src="https://user-images.githubusercontent.com/125088568/225728184-8d4d337f-4cfe-48c8-bd97-8d0d7dbbbfe7.png">
</details>

<img width="804" alt="image" src="https://user-images.githubusercontent.com/125088568/225723217-8c7feddc-c8aa-4109-8140-36ab659bf4a2.png"><br>
<img width="832" alt="image" src="https://user-images.githubusercontent.com/125088568/225723961-43adce65-0f10-4c57-8367-12f68f32e49e.png"><br/>
</details>






<details><summary>행성삭제 화면</summary>

<img width="676" alt="image" src="https://user-images.githubusercontent.com/125088568/225726580-b13d88df-051d-4cfe-8e93-2d7985260a16.png"><br>

## 코드<br>

<details><summary>관리자계정 로그인 시 행성 상세정보 페이지에 삭제버튼 노출</summary>

<img width="587" alt="image" src="https://user-images.githubusercontent.com/125088568/225727714-a9da473f-8425-4f1a-ae31-8e2d4d160f8d.png">
</details>

<img width="538" alt="image" src="https://user-images.githubusercontent.com/125088568/225728639-af629b19-cd01-4069-87d4-4d57b96542db.png"><br>
<img width="700" alt="image" src="https://user-images.githubusercontent.com/125088568/225728531-f3b1fce6-d7fb-4d00-a262-af0a67d8ff4c.png"><br/>
</details>





<details><summary>행성 상세정보 화면</summary>

<img width="538" alt="image" src="https://user-images.githubusercontent.com/125088568/225731296-b2874579-a2f9-4826-a960-0c3ebe883fb6.png"><br>

## 코드<br>

<details><summary>행성 상세 정보</summary>

<img width="819" alt="image" src="https://user-images.githubusercontent.com/125088568/225732431-618961ec-b97a-4ff9-a65e-fbfbded2c58f.png"><br>
<img width="701" alt="image" src="https://user-images.githubusercontent.com/125088568/225732630-b3908c8b-8f59-4d57-8aa6-691ab062cd96.png">
</details>

<details><summary>가격 변동 그래프(최근 7건에 대한 거래만 표시)</summary>

<img width="531" alt="image" src="https://user-images.githubusercontent.com/125088568/225733266-4dd5c923-1ec9-4261-8e2b-e86c2cbb5b85.png">
</details><br/>

</details>





<details><summary>행성 구매</summary>

<img width="689" alt="image" src="https://user-images.githubusercontent.com/125088568/225738749-5e8fe56c-60ea-49c2-96d1-2ed064905c9d.png"><br><br>

## 코드<br>

<details><summary>행성 구매 코드(잔고 차감, 행성 소유주 변경 또는 지정, 판매 버튼 노출, 거래 내역 및 가격 변동 내역 생성)</summary>
#### 구매 후 행성 상태는 미판매 상태가 되도록 구현하였다.<br>

<img width="1006" alt="image" src="https://user-images.githubusercontent.com/125088568/225740514-6ea2d423-352b-4703-9797-a70f2eed219e.png"><br>
<img width="751" alt="image" src="https://user-images.githubusercontent.com/125088568/225741744-1b0630ca-3970-46bd-b414-de054a1a5692.png"><br>
<img width="594" alt="image" src="https://user-images.githubusercontent.com/125088568/225741882-79330497-c835-4e5f-8178-b88761911985.png">
</details>

<details><summary>비로그인 유저가 구매버튼 클릭 시 로그인 페이지로 이동</summary>

<img width="448" alt="image" src="https://user-images.githubusercontent.com/125088568/225737767-0b2370b5-c547-4de5-a465-4eb358e680b2.png">
</details>

<details><summary>잔고가 부족하다면 문구와 함께 버튼 비활성화</summary>

<img width="561" alt="image" src="https://user-images.githubusercontent.com/125088568/225739542-c0334101-3d29-41ee-a948-5eff359cbdf7.png">
</details><br/>

</details>







