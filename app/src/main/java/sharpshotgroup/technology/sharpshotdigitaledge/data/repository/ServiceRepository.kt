package sharpshotgroup.technology.sharpshotdigitaledge.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import sharpshotgroup.technology.sharpshotdigitaledge.data.model.ServiceModel
import java.time.LocalTime

class ServiceRepository {
    private val slots = listOf(LocalTime.of(9, 30), LocalTime.of(11, 0), LocalTime.of(14, 30))

    private val services = listOf(
        service(
            1, "Cybersecurity Readiness Audit", "Cybersecurity", 145.0, 90,
            "Assess exposure, controls, and practical remediation priorities across your organisation.",
            "photo-1563013544-824ae1b704d3",
            listOf("Threat surface review", "Risk-ranked findings", "90-day action plan", "Executive briefing"),
        ),
        service(
            2, "Cloud Architecture Review", "Cloud Solutions", 125.0, 60,
            "Validate cloud design, resilience, cost controls, and governance before your next scale-up.",
            "photo-1451187580459-43490279c0fa",
            listOf("Architecture workshop", "Cost baseline", "Resilience review", "Target-state roadmap"),
        ),
        service(
            3, "Digital Strategy Sprint", "IT Strategy", 180.0, 120,
            "Turn business objectives into a focused technology roadmap with measurable milestones.",
            "photo-1552664730-d307ca884978",
            listOf("Stakeholder alignment", "Capability map", "Investment priorities", "Delivery scorecard"),
        ),
        service(
            4, "Process Automation Discovery", "Optimisation", 110.0, 75,
            "Identify high-value workflows for automation and quantify the operational opportunity.",
            "photo-1516321318423-f06f85e504b3",
            listOf("Workflow mapping", "Automation shortlist", "Value estimate", "Pilot recommendation"),
        ),
        service(
            5, "Data & Analytics Assessment", "Data & AI", 135.0, 90,
            "Improve reporting confidence with a review of data quality, tooling, and decision flows.",
            "photo-1551288049-bebda4e38f71",
            listOf("Data maturity score", "Quality review", "KPI framework", "Analytics roadmap"),
        ),
        service(
            6, "AI Opportunity Workshop", "Data & AI", 160.0, 120,
            "Prioritise responsible AI use cases that solve genuine customer and operational problems.",
            "photo-1677442136019-21780ecad995",
            listOf("Use-case ideation", "Feasibility matrix", "Risk controls", "Pilot blueprint"),
        ),
        service(
            7, "Technology Due Diligence", "IT Strategy", 220.0, 120,
            "Independent technical insight for acquisitions, investment decisions, and transformation programmes.",
            "photo-1521737711867-e3b97375f902",
            listOf("Platform assessment", "Delivery health", "Security posture", "Investment risks"),
        ),
        service(
            8, "Business Continuity Review", "Cybersecurity", 120.0, 75,
            "Test whether systems, suppliers, and teams can recover from a serious disruption.",
            "photo-1497366811353-6870744d04b2",
            listOf("Recovery objectives", "Dependency mapping", "Scenario exercise", "Improvement plan"),
        ),
        service(
            9, "Cloud Cost Optimisation", "Cloud Solutions", 105.0, 60,
            "Reduce avoidable cloud spend while protecting performance and future flexibility.",
            "photo-1558494949-ef010cbdcc31",
            listOf("Spend analysis", "Rightsizing plan", "Governance rules", "Savings forecast"),
        ),
        service(
            10, "Digital Operating Model", "Optimisation", 175.0, 120,
            "Clarify technology ownership, decision rights, team structure, and delivery cadence.",
            "photo-1522071820081-009f0129c71c",
            listOf("Operating model canvas", "Role clarity", "Governance design", "Transition plan"),
        ),
    )

    private fun service(
        id: Int,
        name: String,
        category: String,
        price: Double,
        duration: Int,
        description: String,
        photoId: String,
        features: List<String>,
    ) = ServiceModel(
        id = id,
        name = name,
        description = description,
        price = price,
        availableTime = slots,
        imageUrl = "https://images.unsplash.com/$photoId?auto=format&fit=crop&w=1200&q=85",
        category = category,
        durationMinutes = duration,
        features = features,
    )

    fun observeAll(): Flow<List<ServiceModel>> = flowOf(services)

    fun observeById(id: Int): Flow<ServiceModel?> = flowOf(services.firstOrNull { it.id == id })

    fun getById(id: Int): ServiceModel? = services.firstOrNull { it.id == id }
}
