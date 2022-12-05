```mermaid
graph LR
    Main[<img src='../res/img/main.jpg' width='250' height='550' />]
    SeeResult[<img src='../res/img/see_result.jpg' width='250' height='550' />]
    AddResult[<img src='../res/img/add_result.jpg' width='250' height='550' />]
    SelectTeam[<img src='../res/img/select_team.jpg' width='250' height='550' />]

    Main --> SeeResult
    Main --> AddResult
    
    AddResult -- Select team1, team2 --> SelectTeam
    SeeResult -- Select team --> SelectTeam

    SelectTeam -- Team or cancel --> AddResult
    SelectTeam -- Team or cancel --> SeeResult

    AddResult -- Back --> Main
    SeeResult -- Back --> Main
```