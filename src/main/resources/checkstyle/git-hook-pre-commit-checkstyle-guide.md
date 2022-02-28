## git hook pre-commit에 naver-checkstyle-rule.xml 적용하기

- checkstyle 규칙을 어긴 작업은 commit 되지 않도록 하는 방법입니다.
- checkstyle이 플러그인 되어 있어야 합니다.
-----------------------------

### git hook 적용에 필요한 것(윈도우 기준)

1. 체크스타일 **jar** 파일 
   - [링크](https://github.com/checkstyle/checkstyle/releases/) 에서 **checkstyle-9.0-all.jar** 파일을 찾아 프로젝트/.git/hooks에 저장합니다.

1. **naver-checkstyle-rule.xml**
   - 작업 clone 하면 **src/main/resources/checkstyle/** 에서 확인 가능합니다.

1. **precommit.sample** 파일 스크립트 교체하기
   - [링크](https://gist.github.com/davetron5000/37350) 에서 스크립트를 다운받아 프로젝트/.git/hooks의 **precommit.sample** 내용을
   해당 스크립트로 교체합니다. 
   - .sample은 지우고 저장합니다.

1. git 명령어 실행하기
   - ```
     git config --add checkstyle.jar checkstyle-9.0-all.jar 파일경로 
     git config --add checkstyle.checkfile naver-checkstyle-rule.xml 파일경로
   - (아래는 제 파일경로 예시입니다. ↓)
   - ```
        git config --add checkstyle.jar c:/flabproject/delideli/.git/hooks/checkstyle-9.0-all.jar 
        git config --add checkstyle.checkfile c:/flabproject/delideli/src/main/resources/checkstyle/naver-checkstyle-rules.xml
