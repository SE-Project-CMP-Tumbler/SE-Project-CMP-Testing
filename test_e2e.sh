# write the tests driver here, also make sure to download any needed
# utilities before hand, and delete these utilities later on.
# test other teams' work.

# clean old reports
rm -rf cypress/reports && mkdir cypress/reports  && mkdir cypress/reports/mochareports  
rm -rf  ./reports

# run tests
npx cypress run --quiet --config "baseUrl=http://tumbler.social" 

# combine-reports
mochawesome-merge cypress/reports/mocha/*.json > cypress/reports/mochareports/report.json

# generate-report
marge cypress/reports/mochareports/report.json -f report -o cypress/reports/mochareports

# move report to report folder
mv cypress/reports/mochareports/ ../reports/

# rename html
cd ../reports/
mv ./report.html ./index.html

exit 0
