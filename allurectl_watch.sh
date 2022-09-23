export ALLURE_TOKEN=$(cat ./cfg/token)
export ALLURE_ENDPOINT=$(cat ./cfg/host)
export ALLURE_PROJECT_ID=381
export ALLURE_LAUNCH_NAME="$(DATE) API main page tests local run"
export ALLURE_RESULTS="./build/allure-results"

export TESTS_HOST=https://of.course.i.will.always.love.you/api
export TESTS_BRANCH=master



allurectl watch -- ./gradlew clean test
printenv | grep ALLURE_