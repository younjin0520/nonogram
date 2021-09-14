# nonogram with naver API
### 1. Content
nonogram 게임 안드로이드 애플리케이션입니다. 사용자는 자신의 갤러리에서 선택한 이미지나 naver 검색을 통해 찾은 이미지로 nonogram 게임을 할 수 있습니다. 이 애플리케이션은 안드로이드 스튜디오에서 Java언어로 제작되었습니다.

### 2. UI 및 게임 방법 설명
(1) GAME START 버튼을 누릅니다.

<img src="https://user-images.githubusercontent.com/71880336/132551867-67bf94bd-0ddd-4338-93a9-cea4f3fa454f.jpg" width="300" height="500"/>

(2) 사용자는 하단의 SEARCH 또는 GALLERY 버튼을 클릭하여 게임을 진행할 수 있습니다.


<img src="https://user-images.githubusercontent.com/71880336/132551865-9da7d6f3-ec37-4a47-93bf-2b398701fa5c.jpg" width="300" height="500"/>

[naver 검색을 통해 찾은 이미지로 게임을 진행하기 원하는 경우]
- "Type Query"를 누르고 검색어를 입력한 후 SEARCH 버튼을 누릅니다.
##### 검색어는 영어로 입력합니다.


<img src="https://user-images.githubusercontent.com/71880336/132551864-f98e3ae8-b8c9-4453-a1c9-4c8681b52bab.jpg" width="300" height="500"/>
<img src="https://user-images.githubusercontent.com/71880336/132551862-196e3c09-cf74-4473-9e7f-247b2b12467c.jpg" width="300" height="500"/>

- 이미지와 nonogram 보드판이 화면에 뜨고 게임을 진행할 수 있습니다.

[갤러리의 이미지로 게임을 진행하기 원하는 경우]
- "GALLERY" 버튼을 누릅니다.
- 원하는 사진을 선택하면 이미지와 nonogram 보드판이 화면에 뜨고 게임을 진행할 수 있습니다.

<img src="https://user-images.githubusercontent.com/71880336/132551858-7114dd6a-59a4-47fd-bc92-ff510c450930.jpg" width="300" height="500"/>

##### 잘못된 영역을 눌렀을 경우 "Wrong!" 팝업 메시지와 합께 보드판이 초기화 됩니다.
##### nonogram을 완성했을 경우 "Finish!" 팝업 메시지가 나옵니다.

### 3. 간단한 코드 설명
- BaseAdapter를 상속받은 ImageAdaper와 Gridview를 사용해 보드판 구현
- LinearLayout에 TextView를 넣어 보드판 주변 숫자 구현 (layout_weight이 일정)
- intent를 통해 페이지 전환 구현
- naver에서 제공하는 open API를 통해 검색 기능 구현
- gson을 이용해 json parsing하여 naver에서 이미지 불러오는 기능 구현
- intent를 이용하여 갤러리에서 이미지 불러오기 구현
- Bitmap을 통해 이미지 흑백 변환, 사이즈 변환, 분할, grayscale을 통해 흑백 전환 구현
