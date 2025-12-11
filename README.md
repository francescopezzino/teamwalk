# Team Walk

## Table of Contents

- [User Stories](#features)
- [Technologies Used](#technologies-used)


## User Stories 
1. As the organizer I want to create or remove team step counters So that any team can enter or leave the competition
2. As a team member I want to add steps to my team step counter So that I can help my team win
3. As a team member I want to get my team’s step count at any time So that I can see my team’s score
4. As the organizer I want to get a list of all team scores in descending order

## Technologies Used

team-controller
/teams/addSteps/{employeeId}
/teams/teamscore/{employeeId}

team-management-controller
/admin/teams/removeTeamStepCounter/{teamId}

/admin/teams/leaderboard
Request URL
GET http://localhost:8080/api/v1/admin/teams/leaderboard

No parameters

## team-controller

### 🟠 PUT /api/v1/teams/addSteps/{employeeId}


<details>
<summary>Parameters</summary>
 employeeId

  * integer($int64)
  * (path)

steps
  * string
  * (query)
</details>
<details>
<summary>Response body</summary>

```json
{
  "id": 1,
  "name": "Challenge",
  "teamId": 1,
  "steps": 2000
}
```
        
</details>
<details>
<summary>Curl</summary>

```
curl -X 'PUT' \
  'http://localhost:8080/api/v1/teams/addSteps/1?steps=2000' \
  -H 'accept: */*'
  ```

</details>


### 🟢 POST /api/v1/admin/teams/addTeamStepCounter

No parameters

<details>
<summary>Request body</summary>

```json
{
  "name": "Slow Motion",
  "teamId": 1
}
```

</details>
<details>
<summary>Response body</summary>

```json
{
  "id": 1,
  "name": "Slow Motion",
  "teamId": 1,
  "steps": 0
}
```

</details>

<details>
<summary>Curl</summary>

```
curl -X 'POST' \
'http://localhost:8080/api/v1/admin/teams/addTeamStepCounter' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"name": "Slow Motion",
"teamId": 1
}'
```

</details>

<details>
<summary>Test data</summary>

/api/v1/admin/teams/addTeamStepCounter

```
{
"name": "Challenge",
"teamId": 1
}

{
"name": "Fastpace",
"teamId": 2
}

{
"name": "Slow Motion",
"teamId": 3
}

{
"name": "Endurance",
"teamId": 4
}
```

</details>

## team-management-controller

### 🟢 POST /api/v1/admin/teams

No parameters
<details>
<summary>Request body</summary>

```json
{
  "name": "Sales",
  "employees": [
    {
      "firstName": "Albert",
      "lastName": "White"
    },
    {
      "firstName": "Lara",
      "lastName": "Croft"
    }
  ]
}
```

</details>
<details>
<summary>Response body</summary>

```json
{
  "id": 1,
  "name": "Sales",
  "employees": [
    {
      "id": 1,
      "firstName": "Albert",
      "lastName": "White"
    },
    {
      "id": 2,
      "firstName": "Lara",
      "lastName": "Croft"
    }
  ]
}
```

</details>
<details>
<summary>Curl</summary>

```
curl -X 'POST' \
  'http://localhost:8080/api/v1/admin/teams' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '
  
{
  "name": "Sales",
  "employees": [
    {
      "firstName": "Albert",
      "lastName": "White"
    },
    {
      "firstName": "Lara",
      "lastName": "Croft"
    }
  ]
}'
```
</details>

<details>
<summary>Test data</summary>

/api/v1/admin/teams

```
{
"name": "Marketing",
"employees": [
{
"firstName": "John",
"lastName": "Doe"
},
{
"firstName": "Jane",
"lastName": "Smith"
}
]
}

{
"name": "Management",
"employees": [
{
"firstName": "Maria",
"lastName": "Kelly"
},
{
"firstName": "Alliana",
"lastName": "Gold"
}
]
}

{
"name": "Research",
"employees": [
{
"firstName": "Albert",
"lastName": "White"
},
{
"firstName": "Lara",
"lastName": "Croft"
}
]
}

{
"name": "Sales",
"employees": [
{
"firstName": "Albert",
"lastName": "White"
},
{
"firstName": "Lara",
"lastName": "Croft"
}
]
}
```

</details>

### 🟢 POST /api/v1/admin/teams/addTeamStepCounter

No parameters

<details>
<summary>Request body</summary>

```json
{
  "name": "Slow Motion",
  "teamId": 1
}
</details>
<details>
<summary>Response body</summary>
{
  "id": 1,
  "name": "Slow Motion",
  "teamId": 1,
  "steps": 0
}
```

</details>
<details>
<summary>Curl</summary>

```
curl -X 'POST' \
'http://localhost:8080/api/v1/admin/teams/addTeamStepCounter' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"name": "Slow Motion",
"teamId": 1
}'
```

</details>

<details>
<summary>Test data</summary>

/api/v1/admin/teams/addTeamStepCounter

```json
{
  "name": "Challenge",
  "teamId": 1
}

{
  "name": "Fastpace",
  "teamId": 2
}

{
  "name": "Slow Motion",
  "teamId": 3
}

{
  "name": "Endurance",
  "teamId": 4
}
```

</details>

