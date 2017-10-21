package webhook.repository

import org.springframework.data.mongodb.repository.MongoRepository
import webhook.model.Event

interface EventRepository : MongoRepository<Event, String>